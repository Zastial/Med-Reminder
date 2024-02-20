package com.example.frontend_android.ui.pages.notification.add_edit_notification

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState

@OptIn(ExperimentalMaterial3Api::class)
data class AddEditNotificationState constructor(
    val timePickerState: TimePickerState,
    val isScheduled: Boolean = false
)