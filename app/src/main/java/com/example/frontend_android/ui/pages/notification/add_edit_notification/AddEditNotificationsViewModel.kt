package com.example.frontend_android.ui.pages.notification.add_edit_notification

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
                _state.value = state.value.copy(
                    hour = event.value
                )

            }
            is AddEditNotificationEvent.EnteredMinute -> {
                _state.value = state.value.copy(
                    hour = event.value
                )
            }



            is AddEditNotificationEvent.SaveNotification -> {
                viewModelScope.launch {
                    try {

                        //insert
                        val alarm = AlarmRecord(
                            id = null,
                            title = "",
                            description = "",
                            medicineName = "",
                            isScheduled = true,
                            isRecurring = false,
                            prescription_id = null
                        )
                        alarmDao.insertAlarm(alarm)
                        // schedule

                        //scheduler.schedule(alarm)

                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = "Alarme sauvegardée"
                            )
                        )
                        //_eventFlow.emit(UiEvent.SaveNotification)
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