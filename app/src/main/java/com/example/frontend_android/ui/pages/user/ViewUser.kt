package com.example.frontend_android.ui.pages.user

import androidx.compose.foundation.layout.Column

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.cards.UserSectionCard
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.components.layout.BaseLayout
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