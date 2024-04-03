package com.example.frontend_android.ui.pages.notification.notifications

sealed class AlarmEvent {

    // switch between enabled and disabled
    data class ChangeAlarmState(val alarmId : Long) : AlarmEvent()
}