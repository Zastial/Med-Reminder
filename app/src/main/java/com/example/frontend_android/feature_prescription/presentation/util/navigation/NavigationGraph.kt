package com.example.frontend_android.feature_prescription.presentation.util.navigation




import androidx.compose.runtime.*

import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.frontend_android.feature_prescription.presentation.util.PrescriptionScreen



// Point d'entree du graphe de navigation entre les écrans
@Composable
fun NavigationGraph(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = RootScreen.prescription.route) {

        prescriptionGraph(navController = navController)
        alarmsGraph(navController = navController)



    }
}

