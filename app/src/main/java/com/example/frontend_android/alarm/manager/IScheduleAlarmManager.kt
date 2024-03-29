package com.example.frontend_android.alarm.manager

import com.example.frontend_android.data.model.entities.AlarmRecord

/***
 * Interface pour la gestion des alarmes
 */
interface IScheduleAlarmManager {

    /**
     * Schedule exact alarm
     */
    fun schedule(alarmRecord : AlarmRecord)


    /**
     * Cancel alarm
     */
    fun cancel(alarmRecord: AlarmRecord)
}