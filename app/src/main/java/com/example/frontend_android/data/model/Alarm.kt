package com.example.frontend_android.data.model

import androidx.room.Entity
import java.time.LocalTime


@Entity
data class Alarm(
    val id: Int,
    val title: String,
    val description: String,
    var hour: String = "00",
    var minute: String = "00",
    var medicineName: String,
    var isScheduled: Boolean,
    val isRecurring: Boolean,
    val dates : String
)