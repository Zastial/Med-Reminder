package com.example.frontend_android.ui.pages.notification.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    changeAlarmState: (state : Boolean) -> Unit
) {
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
            modifier = Modifier,
            verticalArrangement = Arrangement.Center
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
                            dayOfWeek = "",
                            changeAlarmState = { changeAlarmState(it) },
                            onClick = { }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
            Button(
                modifier = Modifier,
                onClick = { navController.navigate(Screen.createAlarm.route) }
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



