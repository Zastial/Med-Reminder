package com.example.frontend_android.ui.pages.notification.add_edit_notification
sealed class AddEditNotificationEvent {
    data class EnteredHour(val value: Int): AddEditNotificationEvent()
    data class EnteredMinute(val value: Int): AddEditNotificationEvent()

    data class SelectDayToSchedule(val day : DaysOfWeek): AddEditNotificationEvent()
    object SaveNotification: AddEditNotificationEvent()
    object DeleteNotification: AddEditNotificationEvent()
}