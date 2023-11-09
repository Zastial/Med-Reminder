package com.example.frontend_android.common.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.feature_prescription.presentation.util.NotificationScreen


fun NavGraphBuilder.notificationGraph(
    navController: NavHostController
) {

    navigation(
        route = RootScreen.notification.route,
        startDestination = Screen.notificationScreen.route
    ) {

        composable(route = Screen.notificationScreen.route) {
            NotificationScreen(navController = navController)
        }

    }

}