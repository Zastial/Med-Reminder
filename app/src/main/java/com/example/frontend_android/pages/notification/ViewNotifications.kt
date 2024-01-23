package com.example.frontend_android.pages.notification

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarNavigation
import com.example.frontend_android.components.layout.TopBar
import com.example.frontend_android.layout.BaseLayout

@Composable
fun ViewNotifications(
    navController: NavController
) {
    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Notifications",
                canGoBack = false
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController
            )
        }
    ) {
        Text(text = "Page de notifications")
    }
}