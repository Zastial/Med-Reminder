package com.example.frontend_android.navigation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.frontend_android.ui.pages.notification.add_edit_notification.AddEditNotificationScreen
import com.example.frontend_android.ui.pages.notification.notifications.AlarmEvent
import com.example.frontend_android.ui.pages.notification.notifications.NotificationsScreen
import com.example.frontend_android.ui.pages.notification.notifications.NotificationsViewModel

fun NavGraphBuilder.notificationGraph(
    navController: NavHostController
) {

    navigation(
        route = RootScreen.notification.route,
        startDestination = Screen.viewNotifications.route
    ) {

        composable(route = Screen.viewNotifications.route) {
            val viewModel = hiltViewModel<NotificationsViewModel>()
            NotificationsScreen(
                navController = navController,
                state = viewModel.state.value,
                changeAlarmState = { viewModel.onEvent(AlarmEvent.ChangeAlarmState(it)) }
            )
        }

        composable(
            route = Screen.AddEditAlarm.route + "?alarmId={alarmId}",
            arguments = listOf(
                navArgument(
                    name = "alarmId"
                ) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )

        ) {
            AddEditNotificationScreen(
                navController = navController,
            )
        }

    }

}
