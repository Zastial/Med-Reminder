package com.example.frontend_android.pages.user

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.frontend_android.layout.PageLayout

@Composable
fun ViewUser(
    navController: NavController
) {
    PageLayout(
        title = "Mes Informations",
        canGoBack = false,
        navController = navController
    ) {
        Text(text = "Page utilisateur")
    }

}