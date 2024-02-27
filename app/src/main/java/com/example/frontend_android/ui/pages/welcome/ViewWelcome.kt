package com.example.frontend_android.ui.pages.welcome

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ViewWelcome(
    navController: NavController,
    viewModel: ViewWelcomeModel = hiltViewModel()
) {
    viewModel.PageFromStep(navController)
}