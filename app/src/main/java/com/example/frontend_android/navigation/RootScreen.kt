package com.example.frontend_android.navigation

import androidx.annotation.StringRes
import com.example.frontend_android.R


sealed class RootScreen(val route : String, @StringRes val ressourceID: Int, val icon : Int) {

    object prescription : RootScreen(
        route = "root_prescription",
        ressourceID = R.string.nav_bar_label_prescriptions,
        icon = R.drawable.icon_prescription
    )

    object notification : RootScreen(
        route = "root_notification",
        ressourceID = R.string.nav_bar_label_notification,
        icon = R.drawable.icon_alarm
    )

}