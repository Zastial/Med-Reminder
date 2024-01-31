package com.example.frontend_android.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Alarm")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    var hour: String = "00",
    var minute: String = "00",
    var medicineName: String?,
    var isScheduled: Boolean = false,
    val isRecurring: Boolean = false,
    val dates : String?,
    val prescription_id: Long?,
)

class InvalidAlarmException(message: String): Exception(message)

val defaultAlarm = Alarm(
    id = -1,
    title ="Default title Alarm",
    description = "Default description Alarm",
    medicineName = null,
    dates = null,
    prescription_id = null
)