package com.example.frontend_android.ui.pages.notification.notifications

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.model.dao.AlarmDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
        private val alarmDao: AlarmDao
) : ViewModel() {

    private val _state = mutableStateOf(NotificationState())
    val state: State<NotificationState> = _state



    init {
        Log.e("ALARM", "INIT LOADING ALL ALARMS")
        getAlarms()
    }

    fun onEvent(event : AlarmEvent) {

        when(event) {
            is AlarmEvent.ChangeAlarmState -> {

            }

        }
    }

    private fun getAlarms() {
        val alarmList = alarmDao.getAllAlarms()
        alarmList.onEach { alarm ->
            _state.value = _state.value.copy(
                notificationList = alarm
            )
        }.launchIn(viewModelScope)

        Log.e("ALARM", "Alarm loaded : ${_state.value}")
    }
}