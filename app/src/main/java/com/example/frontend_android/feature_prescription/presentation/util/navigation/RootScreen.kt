package com.example.frontend_android.feature_prescription.presentation.util.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.frontend_android.R


sealed class RootScreen(val route : String, @StringRes val ressourceID: Int, val icon : Int) {

    object prescription : RootScreen(
        route = "root_prescription",
        ressourceID = R.string.nav_bar_label_prescriptions,
        icon = R.drawable.icon_prescription

    )

    object alarms : RootScreen(
        route = "root_alarms",
        ressourceID = R.string.nav_bar_label_alarms,
        icon = R.drawable.icon_alarm
    )
}