package com.example.frontend_android.manager

import com.example.frontend_android.data.model.entities.Alarm

/***
 * Interface pour la gestion des alarmes
 */
interface IScheduleAlarmManager {

    fun schedule(alarm : Alarm)

    fun cancel(alarm: Alarm)
}