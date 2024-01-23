package com.example.frontend_android.pages.user

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarNavigation
import com.example.frontend_android.components.layout.TopBar
import com.example.frontend_android.layout.BaseLayout

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
        Text(text = "Page utilisateur")
    }
}