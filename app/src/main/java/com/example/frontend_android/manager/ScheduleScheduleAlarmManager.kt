package com.example.frontend_android.manager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.frontend_android.GlobalProperties.pendingIntentFlags
import com.example.frontend_android.data.model.entities.Alarm

import com.example.frontend_android.receiver.AlarmBroadcastReceiver
import com.example.frontend_android.receiver.HOUR
import com.example.frontend_android.receiver.IS_RECURRING
import com.example.frontend_android.receiver.MINUTE
import com.example.frontend_android.receiver.TITLE
import java.util.Calendar
import javax.inject.Inject

/**
 * Gestion des alarmes : Planification et annulation
 */

class ScheduleScheduleAlarmManager @Inject constructor(
    private val context: Context,
) : IScheduleAlarmManager {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    override fun schedule(alarm: Alarm) {

        val alarmIntent =  Intent(context, AlarmBroadcastReceiver::class.java).apply {
            putExtra(IS_RECURRING, alarm.isRecurring)
            putExtra(HOUR, alarm.hour)
            putExtra(MINUTE, alarm.minute)
            putExtra(TITLE, alarm.title)
        }

        val alarmPendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            alarmIntent,
            pendingIntentFlags
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, alarm.hour.toInt())
            set(Calendar.MINUTE, alarm.minute.toInt())
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmPendingIntent
        )
    }

    override fun cancel(alarm: Alarm) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alamIntent = Intent(context, AlarmBroadcastReceiver::class.java)

        val alarmPendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            alamIntent,
            pendingIntentFlags,
        )

        val toastText = "Alarm canceled for ${alarm.hour}:${alarm.minute}"

        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()

        alarmManager.cancel(alarmPendingIntent)
    }
}