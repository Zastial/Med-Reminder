package com.example.frontend_android.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "Alarm")
data class AlarmRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    var hour: Int = 0,
    var minute: Int = 0,
    var medicineName: String?,
    var isScheduled: Boolean = false,
    var isRecurring: Boolean = false,
    var daysSelectedJson: String = Gson().toJson(
        mapOf(
            "Mon" to false,
            "Tue" to false,
            "Wed" to false,
            "Thu" to false,
            "Fri" to false,
            "Sat" to false,
            "Sun" to false,
        ),
    ),
    val prescription_id: Long?,
) {
    val daysSelected: Map<String, Boolean>
        get() = Gson().fromJson(
            daysSelectedJson,
            object : TypeToken<Map<String, Boolean>>() {}.type
        )

    fun setDaysSelected(daysSelected: Map<String, Boolean>) {
        this.daysSelectedJson = Gson().toJson(daysSelected)
    }
}


class InvalidAlarmException(message: String): Exception(message)


val defaultAlarmRecord = AlarmRecord(
    title ="Default title Alarm",
    description = "Default description Alarm",
    medicineName = "medicine name",
    prescription_id = null,
    id = 0
)