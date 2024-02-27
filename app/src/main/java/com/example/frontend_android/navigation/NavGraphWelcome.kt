package com.example.frontend_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.ui.pages.welcome.ViewWelcome

fun NavGraphBuilder.welcomeGraph(
    navController : NavHostController
) {

    navigation(
        route = RootScreen.welcome.route,
        startDestination = Screen.viewWelcome.route

    ) {

        composable(
            route = Screen.viewWelcome.route
        ) {
            ViewWelcome(
                navController = navController,
            )
        }
    }
}