package com.example.frontend_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.feature_prescription.presentation.util.NotificationScreen
import com.example.frontend_android.medicine.presentation.MedicineScreen

fun NavGraphBuilder.medicineGraph(
    navController: NavHostController
) {

    navigation(
        route = RootScreen.medicine.route,
        startDestination = Screen.medicineScreen.route
    ) {

        composable(route = Screen.medicineScreen.route) {
            MedicineScreen(navController = navController)
        }

    }

}