package com.example.frontend_android.ui.pages.notification.notifications

import android.app.Notification

data class NotificationState(
    val notification: List<Notification> = emptyList()
)