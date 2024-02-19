package com.example.frontend_android.ui.pages.notification.notifications

import com.example.frontend_android.data.model.entities.AlarmRecord

data class NotificationState(
    val notificationList: List<AlarmRecord> = emptyList()
)