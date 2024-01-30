package com.example.frontend_android.ui.pages.medicines

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.layout.BaseLayout

@Composable
fun ViewMedicines(
    navController: NavController,
    viewModel: ViewMedicinesModel = hiltViewModel()
) {

    val state = viewModel.state.value

    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Médicaments",
                canGoBack = false,
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.medicines) { medicine -> Text(text = medicine.name) }
        }
    }
}