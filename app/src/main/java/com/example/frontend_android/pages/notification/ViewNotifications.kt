package com.example.frontend_android.pages.notification

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.frontend_android.layout.PageLayout

@Composable
fun ViewNotifications(
    navController: NavController
) {

    PageLayout(title = "Notifications", canGoBack = false , navController = navController )
}