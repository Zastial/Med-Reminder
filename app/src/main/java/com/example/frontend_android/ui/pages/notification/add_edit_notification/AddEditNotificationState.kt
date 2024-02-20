package com.example.frontend_android.ui.pages.notification.add_edit_notification

import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
data class AddEditNotificationState constructor(
    val hours: Int = 0,
    val minutes: Int = 0,
    val isScheduled: Boolean = false
)