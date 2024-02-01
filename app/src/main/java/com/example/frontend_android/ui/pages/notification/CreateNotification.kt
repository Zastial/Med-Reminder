package com.example.frontend_android.ui.pages.notification

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.layout.BottomBarValidation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.layout.BaseLayout

@Composable
fun CreateNotificationScreen(
    navController: NavController,
    viewModel: AddEditNotificationsViewModel = hiltViewModel()
) {
    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Ajouter une notification",
                canGoBack = true
            )
        },
        BottomBar = {
            BottomBarValidation(
                navController = navController,
                onValidation = { viewModel },
                onCancellation = {navController.popBackStack()})
        }
    ) {

    }

}
