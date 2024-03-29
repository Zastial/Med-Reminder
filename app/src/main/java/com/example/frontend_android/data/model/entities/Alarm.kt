package com.example.frontend_android.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.frontend_android.ui.pages.notification.add_edit_notification.DaysOfWeek
import com.google.gson.Gson


@Entity(tableName = "Alarm")
data class AlarmRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    var title: String,
    var description: String,
    var hours: Int = 0,
    var minutes: Int = 0,
    var medicineName: String?,
    var isScheduled: Boolean = false,
    var isRecurring: Boolean = false,
    var daysSelectedJson: String = "[]",
    val prescription_id: Long?,
) {
   val daysSelected: MutableList<DaysOfWeek>
        get() = Gson().fromJson(daysSelectedJson, Array<DaysOfWeek>::class.java).toMutableList()
}


class InvalidAlarmException(message: String): Exception(message)


val defaultAlarmRecord = AlarmRecord(
    id = 0,
    title ="Default title Alarm",
    description = "Default description Alarm",
    medicineName = "medicine name",
    prescription_id = null
)