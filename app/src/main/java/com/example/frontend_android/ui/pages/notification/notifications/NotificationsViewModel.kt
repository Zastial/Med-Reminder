package com.example.frontend_android.ui.pages.notification.notifications

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.model.dao.AlarmDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
        private val alarmDao: AlarmDao
) : ViewModel() {

    private val _state = mutableStateOf(NotificationState())
    val state: State<NotificationState> = _state

    private var getAlarmJob: Job? = null

    init {
        getAlarms()
    }

    fun onEvent(event : AlarmEvent) {

        when(event) {
            is AlarmEvent.ChangeAlarmState -> {
                Log.e("ALARM", "change alarm state of alarm : ${event.alarmId}")
                viewModelScope.launch {
                    try {
                        val alarmToUpdate = alarmDao.getAlarmById(event.alarmId)
                        if (alarmToUpdate != null) {
                            Log.e("ALARM", "before insert state : ${state.value.notificationList}" )
                            alarmDao.insertAlarm(alarmToUpdate.copy(isScheduled = !alarmToUpdate.isScheduled))
                        } else {
                            Log.e("ALARM", "Problème dans la déactivation de l'alarme : ${event.alarmId}")
                        }
                    } catch (e : Exception) {
                        Log.e("ALARM", "Erreur pendant la désactivation de l'alarme")
                    }
                }
            }

        }
    }

    private fun getAlarms() {
        getAlarmJob?.cancel()
        getAlarmJob = alarmDao.getAllAlarms().onEach { alarm ->
            _state.value = state.value.copy(
               notificationList = alarm
            )
        }.launchIn(viewModelScope)
    }
}