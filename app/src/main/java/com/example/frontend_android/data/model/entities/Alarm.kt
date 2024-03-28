package com.example.frontend_android.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.frontend_android.ui.pages.notification.add_edit_notification.DaysOfWeek
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


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
    var daysSelectedJson: String = Gson().toJson(
        mapOf(
            DaysOfWeek.LUNDI to false,
            DaysOfWeek.MARDI to false,
            DaysOfWeek.MERCREDI to false,
            DaysOfWeek.JEUDI to false,
            DaysOfWeek.VENDREDI to false,
            DaysOfWeek.SAMEDI to false,
            DaysOfWeek.DIMANCHE to false,
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
    id = 0,
    title ="Default title Alarm",
    description = "Default description Alarm",
    medicineName = "medicine name",
    prescription_id = null
)