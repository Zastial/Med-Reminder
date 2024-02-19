package com.example.frontend_android.alarm.manager

import com.example.frontend_android.data.model.entities.AlarmRecord

/***
 * Interface pour la gestion des alarmes
 */
interface IScheduleAlarmManager {

    fun schedule(alarmRecord : AlarmRecord)

    fun cancel(alarmRecord: AlarmRecord)
}