package com.example.frontend_android.manager

import com.example.frontend_android.data.model.Alarm

interface IScheduleAlarmManager {

    fun schedule(alarm : Alarm)

    fun cancel(alarm: Alarm)
}