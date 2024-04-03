package com.example.frontend_android.ui.pages.notification.add_edit_notification

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.frontend_android.data.model.entities.Prescription

@OptIn(ExperimentalMaterial3Api::class)
data class AddEditNotificationState constructor(
    val alarmId : Long? = -1L,
    val hours: Int = 0,
    val minutes: Int = 0,
    val isScheduled: Boolean = true,
    val isRecuring: Boolean = true,
    val scheduledDays : SnapshotStateList<DaysOfWeek> = mutableStateListOf(),
    val prescriptionId : Long? = -1L,
    val prescriptionsList : List<Prescription> = listOf()
)