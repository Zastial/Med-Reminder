package com.example.frontend_android.pages.prescription

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarValidation
import com.example.frontend_android.components.layout.TopBar
import com.example.frontend_android.layout.BaseLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionModel = hiltViewModel()
) {
    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Ajouter une ordonnance",
                canGoBack = true
            )
        },
        BottomBar = {
            BottomBarValidation(
                navController = navController,
                onValidation = { },
                onCancellation = {})
        }
    ) {

    }


}