package com.example.frontend_android.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.ui.pages.notification.ViewNotifications


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

    }

}