package com.example.frontend_android.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.prescription.presentation.screens.AddEditPrescriptionScreen
import com.example.frontend_android.feature_prescription.presentation.util.PrescriptionScreen


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
            AddEditPrescriptionScreen(
                id = "aa",
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}