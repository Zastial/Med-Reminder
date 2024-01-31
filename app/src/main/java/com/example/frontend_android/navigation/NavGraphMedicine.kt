package com.example.frontend_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.frontend_android.ui.pages.medicines.ViewMedicines

fun NavGraphBuilder.medicineGraph(
    navController: NavHostController
) {

    navigation(
        route = RootScreen.medicine.route,
        startDestination = Screen.viewMedicines.route
    ) {

        composable(route = Screen.viewMedicines.route) {
            ViewMedicines(navController = navController)
        }

    }

}