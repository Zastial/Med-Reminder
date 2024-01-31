package com.example.frontend_android.ui.pages.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.cards.UserSectionCard
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.layout.BaseLayout
import com.example.frontend_android.navigation.Screen

@Composable
fun ViewUser(
    navController: NavController
) {
    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Informations utilisateur",
                canGoBack = false
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController
            )
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            UserSectionCard("Informations personnelles", navController , Screen.viewUserInformations.route)
            UserSectionCard("Contacter Médecin", navController , Screen.viewUserDoctorContact.route)
            UserSectionCard("Historique effets secondaires", navController , Screen.viewUserSideEffectsHistory.route)
        }
    }
}