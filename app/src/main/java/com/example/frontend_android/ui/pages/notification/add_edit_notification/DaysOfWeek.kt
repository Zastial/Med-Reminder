package com.example.frontend_android.ui.pages.notification.add_edit_notification

enum class DaysOfWeek {
    LUNDI,
    MARDI,
    MERCREDI,
    JEUDI,
    VENDREDI,
    SAMEDI,
    DIMANCHE
}

/**
 * Retourne la lettre correspondant au jour
 */
fun DaysOfWeek.display() : String {
    return when (this) {
        DaysOfWeek.LUNDI -> "L"
        DaysOfWeek.MARDI ->  "M"
        DaysOfWeek.MERCREDI -> "M"
        DaysOfWeek.JEUDI -> "J"
        DaysOfWeek.VENDREDI -> "V"
        DaysOfWeek.SAMEDI -> "S"
        DaysOfWeek.DIMANCHE ->"D"
    }
}