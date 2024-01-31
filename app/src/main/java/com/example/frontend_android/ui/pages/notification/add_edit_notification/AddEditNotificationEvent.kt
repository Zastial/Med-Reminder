package com.example.frontend_android.ui.pages.notification.add_edit_notification
sealed class    AddEditNotificationEvent {
    data class EnteredHour(val value: String): AddEditNotificationEvent()
    data class EnteredMinute(val value: String): AddEditNotificationEvent()

    object SaveNotification: AddEditNotificationEvent()
}