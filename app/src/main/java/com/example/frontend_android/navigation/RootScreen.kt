package com.example.frontend_android.navigation

import androidx.annotation.StringRes
import com.example.frontend_android.R


/*
 * This is the screens that are displayed on the bottom bar of
 * the application.
 */
sealed class RootScreen(
    val route: String,
    @StringRes val ressourceID: Int,
    val icon: Int,
    val isInBottomBar: Boolean = true
) {

    object welcome : RootScreen(
        route = "root_welcome",
        ressourceID = R.string.nav_bar_label_welcome,
        icon = R.drawable.ic_welcome,
        isInBottomBar = false,
    )

    object prescription : RootScreen(
        route = "root_prescription",
        ressourceID = R.string.nav_bar_label_prescriptions,
        icon = R.drawable.ic_prescription
    )

    object notification : RootScreen(
        route = "root_notification",
        ressourceID = R.string.nav_bar_label_notification,
        icon = R.drawable.ic_alarm
    )
    object medicine : RootScreen(
        route = "root_medicine",
        ressourceID = R.string.nav_bar_label_medicine,
        icon = R.drawable.ic_pill
    )

    object user : RootScreen(
        route = "root_user",
        ressourceID = R.string.nav_bar_label_user,
        icon = R.drawable.ic_account_circle
    )


}