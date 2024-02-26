package com.example.frontend_android.navigation


sealed class Screen(val route: String) {

    //Screen pour toutes les ordonnances
    object viewPrescriptions: Screen(
        route ="prescriptions_screen"
    )
    //Screen pour une seule ordonnance
    object createPrescription: Screen(
        route = "create_prescription_screen"
    )
    object modifyPrescription: Screen(
        route = "modify_prescription_screen"
    )
    object viewPhotoModule: Screen(
        route = "prescription_photo_module_screen"
    )

    // Screen pour toutes les alarmes
    object viewNotifications: Screen(
        route = "alarms_screen"
    )

    // Screen par défaut
    object viewMedicines: Screen(
        route = "medicine_screen"
    )

    object viewMedicineInformations: Screen(
        route = "medicine_informations_screen/{medicine_cis}",
    )

    object viewUser: Screen(
        route = "user_screen"
    )

    object viewUserInformations: Screen(
        route = "user_informations_screen"
    )

    object viewUserDoctorContact: Screen(
        route = "user_doctor_contact_screen"
    )

    object viewUserSideEffectsHistory: Screen(
        route = "user_side_effects_history_screen"
    )

    object createAlarm: Screen(
        route = "crate_notification"
    )
}
