package com.example.frontend_android.feature_prescription.presentation.util

sealed class Screen(val route: String) {

    // Ecran d'affichage des ordonnances
    object prescriptionScreen: Screen("prescription_screen")

    object otherScreen: Screen("otherScreen")
}
