package com.example.frontend_android.ui.pages.notification.add_edit_notification

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.alarm.manager.IScheduleAlarmManager
import com.example.frontend_android.data.model.dao.AlarmDao
import com.example.frontend_android.data.model.dao.PrescriptionDao
import com.example.frontend_android.data.model.entities.AlarmRecord
import com.example.frontend_android.data.model.entities.InvalidAlarmException
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNotificationsViewModel @Inject constructor(
    private val alarmDao: AlarmDao,
    private val prescriptionDao: PrescriptionDao,
    private val alarmScheduler: IScheduleAlarmManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddEditNotificationState())
    val state: State<AddEditNotificationState> = _state

    private var getPrescriptionJob: Job? = null


    // permet de gérer les evenements
    // ex : quand on sauvegarde une alarme
    // on affiche un Toast
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()



    init {
        savedStateHandle.get<Long>("alarmId")?.let { alarmId ->
            if (alarmId != -1L) {
                Log.d("ALARM", "INIT ViewModel")
                viewModelScope.launch {
                    getCurrentAlarm(alarmId)
                    getAllPrescriptions()
                }
            }
        }
    }


    fun onEvent(event : AddEditNotificationEvent) {

        when(event){
            is AddEditNotificationEvent.EnteredHour -> {
                _state.value = state.value.copy(
                    hours = event.value
                )
            }
            is AddEditNotificationEvent.EnteredMinute -> {
                _state.value = state.value.copy(
                    minutes = event.value
                )
            }
            is AddEditNotificationEvent.SaveNotification -> {
                viewModelScope.launch {
                    try {
                        val alarmToInsert = AlarmRecord(
                            id = if (_state.value.alarmId == -1L) null else _state.value.alarmId,
                            title = "",
                            description = "",
                            medicineName = "",
                            hours = _state.value.hours,
                            minutes = _state.value.minutes,
                            isScheduled = _state.value.isScheduled,
                            isRecurring = _state.value.isRecuring,
                            prescription_id = if (_state.value.alarmId == -1L) null else _state.value.prescriptionId,
                            daysSelectedJson = Gson().toJson(_state.value.scheduledDays.toList())
                        )
                        Log.e("ALARM", "ALARM to INSERT, prescriptionID : ${alarmToInsert.prescription_id} ")
                        val alarmId = alarmDao.insertAlarm(alarmToInsert)
                        val alarmToSchedule = alarmDao.getAlarmById(alarmId)
                            ?: throw InvalidAlarmException("L'alarme a planifiée est nulle")

                        alarmScheduler.schedule(alarmToSchedule)

                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = "Alarme sauvegardée"
                            )
                        )

                    } catch (e : InvalidAlarmException) {

                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Impossible de sauvegarder l'alarme"
                            )
                        )
                    }

                }
            }
            is AddEditNotificationEvent.DeleteNotification -> {
                viewModelScope.launch {
                    try {
                        val alarmToDelete = _state.value.alarmId?.let { alarmDao.getAlarmById(it) }
                        if (alarmToDelete != null) {
                            alarmScheduler.cancel(alarmToDelete)
                            alarmDao.deleteAlarm(alarmToDelete)
                        }
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = "TODO"
                            )
                        )
                    } catch (e : InvalidAlarmException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Impossible de supprimer l'alarme"
                            )
                        )
                    }
                }
            }

            is AddEditNotificationEvent.SelectDayToSchedule -> {
                Log.d("ALARM", "day clicked: $event")
                val copyValue = state.value.copy()
                if (state.value.scheduledDays.contains(event.day)) {
                    copyValue.scheduledDays.remove(event.day)
                } else {
                    copyValue.scheduledDays.add(event.day)
                }
                _state.value = copyValue
                Log.d("ALARM", "private days states: ${_state.value.scheduledDays} with time : ${_state.value.hours} : ${_state.value.minutes}")
                Log.d("ALARM", "public days states: ${state.value.scheduledDays} with time : ${state.value.hours} : ${state.value.minutes}")
            }
            is AddEditNotificationEvent.SelectPrescriptionToLink -> {
                _state.value = state.value.copy(
                    prescriptionId = event.prescriptionId
                )
            }
        }



    }

    private suspend fun getCurrentAlarm(alarmId: Long) {

            alarmDao.getAlarmById(alarmId)?.also {

                _state.value = state.value.copy(
                    alarmId = it.id ?: alarmId,
                    hours = it.hours,
                    minutes = it.minutes,
                    isScheduled = it.isScheduled,
                    scheduledDays = it.daysSelected.toMutableStateList(),
                    prescriptionId = it.prescription_id
                )
            }
            Log.e("ALARM VM recieve", _state.value.toString())
    }
    private fun getAllPrescriptions() {

        getPrescriptionJob?.cancel()
        getPrescriptionJob = prescriptionDao.getPrescriptions().onEach { prescriptions ->
            _state.value = state.value.copy(
                prescriptionsList = prescriptions
            )
        }.launchIn(viewModelScope)

    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String): UiEvent()
    }
}