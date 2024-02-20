package com.example.frontend_android.alarm.manager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.frontend_android.GlobalProperties.pendingIntentFlags
import com.example.frontend_android.alarm.receiver.AlarmBroadcastReceiver
import com.example.frontend_android.alarm.receiver.HOUR
import com.example.frontend_android.alarm.receiver.IS_RECURRING
import com.example.frontend_android.alarm.receiver.MINUTE
import com.example.frontend_android.alarm.receiver.TITLE
import com.example.frontend_android.data.model.entities.AlarmRecord
import java.util.Calendar
import javax.inject.Inject

/**
 * Gestion des alarmes : Planification et annulation
 *
 */

class ScheduleAlarmManager @Inject constructor(
    private val context: Context,
) : IScheduleAlarmManager {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    override fun schedule(alarmRecord: AlarmRecord) {
        if (alarmRecord.id == null) {
            throw IllegalArgumentException("L'id de l'alarme est nulle")
        }

        val alarmIntent =  Intent(context, AlarmBroadcastReceiver::class.java).apply {
            putExtra(IS_RECURRING, alarmRecord.isRecurring)
            putExtra(HOUR, alarmRecord.hours)
            putExtra(MINUTE, alarmRecord.minutes)
            putExtra(TITLE, alarmRecord.title)
        }

        val alarmPendingIntent = PendingIntent.getBroadcast(
            context,
            alarmRecord.id.toInt(),
            alarmIntent,
            pendingIntentFlags
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, alarmRecord.hours)
            set(Calendar.MINUTE, alarmRecord.minutes)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmPendingIntent
        )
    }

    override fun cancel(alarmRecord: AlarmRecord) {
        if (alarmRecord.id == null) {
            throw IllegalArgumentException("L'id de l'alarme est nulle")
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alamIntent = Intent(context, AlarmBroadcastReceiver::class.java)

        val alarmPendingIntent = PendingIntent.getBroadcast(
            context,
            alarmRecord.id.toInt(),
            alamIntent,
            pendingIntentFlags,
        )

        val toastText = "Alarm canceled for ${alarmRecord.hours}:${alarmRecord.minutes}"

        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()

        alarmManager.cancel(alarmPendingIntent)
    }
}