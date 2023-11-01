package com.example.frontend_android.feature_prescription.presentation.util.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.feature_prescription.presentation.util.PrescriptionScreen
import com.example.frontend_android.feature_prescription.presentation.util.screens.addEditPrescriptionScreen

// graphe s'occupant des ordonnances
fun NavGraphBuilder.prescriptionGraph(
    navController : NavHostController
) {

    navigation(
        route = RootScreen.prescription.route,
        startDestination = Screen.prescriptionsScreen.route

    ) {

        composable(
            route = Screen.prescriptionsScreen.route
        ) {
            PrescriptionScreen(
                navController = navController,
                showPrescription = {
                    navController.navigate(Screen.addEditPrescriptionScreen.route)
                }
            )
        }

        composable(
            route = Screen.addEditPrescriptionScreen.route
        ) {
            addEditPrescriptionScreen(
                id = "aa",
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}