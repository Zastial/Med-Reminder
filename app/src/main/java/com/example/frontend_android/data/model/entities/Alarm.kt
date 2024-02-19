package com.example.frontend_android.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Alarm")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    var hour: Int = 0,
    var minute: Int = 0,
    var medicineName: String?,
    var isScheduled: Boolean = false,
    var isRecurring: Boolean = false,
    var mond: Boolean,
    var tues: Boolean,
    var wedn: Boolean,
    var thur: Boolean,
    var frid: Boolean,
    var satu: Boolean,
    var sund: Boolean,
    val prescription_id: Long?,
)

class InvalidAlarmException(message: String): Exception(message)

val defaultAlarm = Alarm(
    id = -1,
    title ="Default title Alarm",
    description = "Default description Alarm",
    medicineName = null,
    mond = false,
    tues = false,
    wedn = false,
    thur = false,
    frid = false,
    satu = false,
    sund = false,
    prescription_id = null
)