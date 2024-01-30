package com.example.frontend_android.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime


@Entity(tableName = "Alarm")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    var hour: String = "00",
    var minute: String = "00",
    var medicineName: String,
    var isScheduled: Boolean,
    val isRecurring: Boolean,
    val dates : String,
    val prescription_id: Long,
)

class InvalidAlarmException(message: String): Exception(message)