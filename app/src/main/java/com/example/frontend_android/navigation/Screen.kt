package com.example.frontend_android.navigation


sealed class Screen(val route: String) {

    //Screen pour toutes les ordonnances
    object prescriptionsScreen: Screen(
        route ="prescriptions_screen"
    )
    //Screen pour une seule ordonnance
    object addEditPrescriptionScreen: Screen(
        route = "prescription_screen"
    )


    // Screen pour toutes les alarmes
    object notificationScreen: Screen(
        route = "alarms_screen"
    )

    // Screen par défaut
    object medicineScreen: Screen(
        route = "medicine_screen"
    )

    object userScreen: Screen(
        route = "user_screen"
    )

}
