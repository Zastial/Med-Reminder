package com.example.frontend_android.ui.pages.user

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.layout.BaseLayout

@Composable
fun ViewUserDoctorContact(
    navController: NavController
) {

    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Contact docteur",
                canGoBack = true,
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController,
            )
        },
    ) {
        Text(text = "Page contact docteur")
    }


}