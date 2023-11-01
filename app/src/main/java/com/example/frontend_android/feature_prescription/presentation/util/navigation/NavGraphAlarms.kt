package com.example.frontend_android.feature_prescription.presentation.util.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.feature_prescription.presentation.util.AlarmScreen

fun NavGraphBuilder.alarmsGraph(
    navController: NavHostController
) {

    navigation(
        route = RootScreen.alarms.route,
        startDestination = Screen.alarmsScreen.route
    ) {

        composable(route = Screen.alarmsScreen.route) {
            AlarmScreen(navController = navController)
        }

    }

}