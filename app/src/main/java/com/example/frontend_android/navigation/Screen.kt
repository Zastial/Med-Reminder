package com.example.frontend_android.navigation


sealed class Screen(val route: String) {

    //Screen pour toutes les ordonnances
    object viewPrescriptions: Screen(
        route ="prescriptions_screen"
    )
    //Screen pour une seule ordonnance
    object createPrescription: Screen(
        route = "prescription_screen"
    )


    // Screen pour toutes les alarmes
    object viewNotifications: Screen(
        route = "alarms_screen"
    )

    // Screen par défaut
    object viewMedicines: Screen(
        route = "medicine_screen"
    )

    object viewUser: Screen(
        route = "user_screen"
    )

    object viewUserInformations: Screen(
        route = "user_informations_screen"
    )

}
