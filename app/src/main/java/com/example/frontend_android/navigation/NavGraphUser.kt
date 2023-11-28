package com.example.frontend_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.medicine.presentation.MedicineScreen
import com.example.frontend_android.user.presentation.UserScreen

fun NavGraphBuilder.userGraph(
    navController: NavHostController
) {

    navigation(
        route = RootScreen.user.route,
        startDestination = Screen.userScreen.route
    ) {

        composable(route = Screen.userScreen.route) {
            UserScreen(navController = navController)
        }

    }

}