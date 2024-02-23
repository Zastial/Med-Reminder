package com.example.frontend_android.alarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.frontend_android.alarm.service.AlarmService
import java.util.Calendar

class AlarmBroadcastReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {
        val alarmService = AlarmService(context = context)
        try {
            val title = intent?.getStringExtra(TITLE) ?: return
            val text = intent?.getStringExtra(CONTENT) ?: return
            alarmService.showNotification(title, text)
            Log.d("ALARM", "Alarm recieve at : ${Calendar.getInstance().time},  title : ${title}")
        } catch (e : Exception) {
            Log.d("ALARM", "Fail to recieve alarm in broadCast Reciever : ${e.printStackTrace()}")
        }
    }




}



const val IS_RECURRING = "IS_RECURRING"
const val DAYS_SELECTED = "DAYS_SELECTED"
const val TITLE = "TITLE"
const val CONTENT = "CONTENT"
const val HOUR = "HOUR"
const val MINUTE = "MINUTE"