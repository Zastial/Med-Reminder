package com.example.frontend_android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.frontend_android.alarm.receiver.AlarmBroadcastReceiver
import com.example.frontend_android.alarm.receiver.INTENT_FILTER_MED_AP
import com.example.frontend_android.alarm.service.AlarmService

import com.example.frontend_android.navigation.NavigationGraph
import com.example.frontend_android.ui.theme.MedreminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerReceiver(AlarmBroadcastReceiver(), IntentFilter(INTENT_FILTER_MED_AP), RECEIVER_NOT_EXPORTED)
        createNotificationChanel()



        setContent {
            MedreminderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    private fun createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                AlarmService.COUNTER_CHANNEL_ID,
                "Medicine Alarm",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Used to notify the user that he should take medicine"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        } else {
            Toast.makeText(this, "Impossible de créer des notifications, vueillez contacter l'administrateur de l'application", Toast.LENGTH_SHORT).show()
        }

    }
}
