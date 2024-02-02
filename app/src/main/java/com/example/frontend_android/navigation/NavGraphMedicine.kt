package com.example.frontend_android.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.frontend_android.ui.pages.medicines.ViewMedicineInformations
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

        composable(
            route = Screen.viewMedicineInformations.route,
            arguments = listOf(navArgument("medicine_cis") { type = NavType.LongType })
        ) {
            ViewMedicineInformations(navController = navController)
        }

    }

}