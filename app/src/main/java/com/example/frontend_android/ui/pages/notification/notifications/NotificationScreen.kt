package com.example.frontend_android.ui.pages.notification.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.ui.components.cards.AlarmCard
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.theme.MedreminderTheme

@Composable
fun NotificationsScreen(
    navController: NavController,
    state: NotificationState,
    changeAlarmState: (alarmId : Long) -> Unit
) {
    val context = LocalContext.current
    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Notifications",
                canGoBack = false
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                state.notificationList.forEach { alarm ->
                    item {
                        AlarmCard(
                            modifier = Modifier,
                            hour = alarm.hours,
                            minutes = alarm.minutes,
                            isActive = alarm.isScheduled,
                            dayOfWeek = alarm.daysSelected,
                            changeAlarmState = { alarm.id?.let { it1 -> changeAlarmState(it1) } },
                            onClick = { navController.navigate(
                                Screen.AddEditAlarm.route + "?alarmId=${alarm.id}")
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(12.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.AddEditAlarm.route)
                }
            ) {
                Text(text = "Ajouter une notification")
            }
        }
    }
}




@Preview(showBackground = true, )
@Composable
fun NotificationPreview() {
    val navController = rememberNavController()
    MedreminderTheme {
        NotificationsScreen(
            navController = navController,
            state = NotificationState(),
            changeAlarmState = { }
        )
    }
}



