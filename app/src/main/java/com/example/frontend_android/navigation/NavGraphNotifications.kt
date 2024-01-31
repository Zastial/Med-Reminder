package com.example.frontend_android.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.ui.pages.notification.add_edit_notification.AddEditNotificationsViewModel
import com.example.frontend_android.ui.pages.notification.add_edit_notification.CreateNotificationScreen
import com.example.frontend_android.ui.pages.notification.notifications.ViewNotifications
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toCollection
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
            ViewNotifications(navController = navController)
        }

        composable(route = Screen.createAlarm.route) {
            val viewModel  = hiltViewModel<AddEditNotificationsViewModel>()
            val state by viewModel.state.collectAsState()

            val eventFlow by viewModel.eventFlow.collect()
            CreateNotificationScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent,
                viewModelEvent = eventFlow
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