package com.example.frontend_android.ui.pages.user

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.ui.components.cards.UserSectionCard
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar

@Composable
fun ViewUser(
    navController: NavController
) {
    val context = LocalContext.current
    val packageManager = context.packageManager
    val sharedPreferences = context.getSharedPreferences("user_infos", Context.MODE_PRIVATE)
    val userInfos = sharedPreferences.all

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(userInfos["docteur_email"]))
    }

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
            UserSectionCard("Contacter Médecin", null, null) {
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(context, intent, null)
                }
            }
            UserSectionCard("Historique effets secondaires", navController , Screen.viewUserSideEffectsHistory.route)
        }
    }
}