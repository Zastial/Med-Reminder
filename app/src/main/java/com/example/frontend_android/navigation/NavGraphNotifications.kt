package com.example.frontend_android.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.ui.pages.notification.add_edit_notification.AddEditNotificationScreen
import com.example.frontend_android.ui.pages.notification.notifications.NotificationScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun NavGraphBuilder.notificationGraph(
    navController: NavHostController
) {

    navigation(
        route = RootScreen.notification.route,
        startDestination = Screen.viewNotifications.route
    ) {

        composable(route = Screen.viewNotifications.route) {
            NotificationScreen(navController = navController)
        }

        composable(route = Screen.createAlarm.route) {
            AddEditNotificationScreen(
                navController = navController
            )
        }

    }

}

@Composable
fun <T> Flow<T>.collectAsEffect(
    context: CoroutineContext = EmptyCoroutineContext,
    block: (T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onEach(block).flowOn(context).launchIn(this)
    }
}