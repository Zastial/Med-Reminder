package com.example.frontend_android.feature_prescription.presentation.util.navigation


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
    object alarmsScreen: Screen(
        route = "alarms_screen"
    )

    // Screen par défaut
    object otherScreen: Screen(
        route = "otherScreen"
    )

}
