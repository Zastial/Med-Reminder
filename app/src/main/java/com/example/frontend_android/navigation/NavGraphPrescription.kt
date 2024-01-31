package com.example.frontend_android.navigation

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.frontend_android.pages.prescription.CreatePrescriptions
import com.example.frontend_android.ui.pages.prescription.ViewPrescriptions


// graphe s'occupant des ordonnances
fun NavGraphBuilder.prescriptionGraph(
    navController : NavHostController
) {

    navigation(
        route = RootScreen.prescription.route,
        startDestination = Screen.viewPrescriptions.route

    ) {

        composable(
            route = Screen.viewPrescriptions.route
        ) {
            ViewPrescriptions(
                navController = navController,
            )
        }

        composable(
            route = Screen.createPrescription.route
        ) {
            CreatePrescriptions(
                navController = navController
            )
        }
    }
}