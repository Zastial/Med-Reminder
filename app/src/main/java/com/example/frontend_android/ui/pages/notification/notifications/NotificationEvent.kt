package com.example.frontend_android.ui.pages.notification.notifications

sealed class NotificationEvent {

    // switch between enabled and disabled
    data class ToggleAlarmState(val value: Boolean): NotificationEvent()
}