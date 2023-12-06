package com.example.frontend_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.pages.user.ViewUser
import com.example.frontend_android.pages.user.ViewUserInformations

fun NavGraphBuilder.userGraph(
    navController: NavHostController
) {

    navigation(
        route = RootScreen.user.route,
        startDestination = Screen.viewUser.route
    ) {

        composable(route = Screen.viewUser.route) {
            ViewUser(navController = navController)
        }

        composable(route = Screen.viewUserInformations.route) {
            ViewUserInformations(navController = navController)
        }

    }

}