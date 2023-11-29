package com.example.frontend_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.pages.prescription.CreatePrescriptions
import com.example.frontend_android.pages.prescription.ViewPrescriptions


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