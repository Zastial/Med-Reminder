package com.example.frontend_android.ui.pages.notification.add_edit_notification

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.alarm.manager.IScheduleAlarmManager
import com.example.frontend_android.data.model.dao.AlarmDao
import com.example.frontend_android.data.model.entities.AlarmRecord
import com.example.frontend_android.data.model.entities.InvalidAlarmException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNotificationsViewModel @Inject constructor(
    private val alarmDao: AlarmDao,
    private val scheduler: IScheduleAlarmManager
) : ViewModel() {

    private val _state =   mutableStateOf(AddEditNotificationState())
    val state: State<AddEditNotificationState> = _state

    // permet de gérer les evenements
    // ex : quand on sauvegarde une alarme
    // on affiche un Toast
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event : AddEditNotificationEvent) {

        when(event){
            is AddEditNotificationEvent.EnteredHour -> {
                Log.d("ALARM", "entered hour: $event")
                _state.value = state.value.copy(
                    hours = event.value
                )

            }
            is AddEditNotificationEvent.EnteredMinute -> {
                Log.d("ALARM", "entered minutes: $event")

                _state.value = state.value.copy(
                    minutes = event.value
                )
            }



            is AddEditNotificationEvent.SaveNotification -> {
                Log.d("ALARM", "VM save notification: $event")

                viewModelScope.launch {
                    try {

                        //insert
                        val alarmToInsert = AlarmRecord(
                            id = null,
                            title = "",
                            description = "",
                            medicineName = "",
                            hours = event.hours,
                            minutes = event.minutes,
                            isScheduled = true,
                            isRecurring = false,
                            prescription_id = null
                        )
                        val alarmId = alarmDao.insertAlarm(alarmToInsert)
                        val alarmToSchedule = alarmDao.getAlarmById(alarmId)
                            ?: throw InvalidAlarmException("L'alarme a planifiée est nulle")

                        scheduler.schedule(alarmToSchedule)
                        Log.d("ALARM", "ALARM viewModel scheduled : ${alarmToSchedule}")

                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = "Alarme sauvegardée"
                            )
                        )

                        _eventFlow.emit(UiEvent.SaveNotification)
                    } catch (e : InvalidAlarmException) {

                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Impossible de sauvegarder l'alarme"
                            )
                        )
                    }

                }
            }
        }



    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveNotification: UiEvent()
    }
}