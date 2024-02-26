package com.example.frontend_android.ui.pages.prescription

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.TopBarPrescriptionNavigation
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarStepNavigation
import com.example.frontend_android.ui.components.layout.BottomBarValidation

@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    
    fun loadingPage() {
        if (Uri.EMPTY.equals(state.imageUri) || state.imageUri == null) {
            viewModel.changeStep(2)
        } else {
            viewModel.nextPage()
        }
    }

    @Composable
    fun BottomBar(step : Int) {
        when (step) {
            0 -> BottomBarStepNavigation(
                navController = navController,
                onClick = { loadingPage() },
            )
            1 -> {}
            6 -> BottomBarValidation(
                navController = navController,
                onValidation = { viewModel.insertPrescription() },
                onCancellation = { viewModel.previousPage() }
            )
            7 -> {}
            else -> BottomBarStepNavigation(
                navController = navController,
                onClick = { viewModel.nextPage() },
            )
        }
    }

    BaseLayout(
        TopBar = {
            TopBarPrescriptionNavigation(
                navController = navController,
                title = "Ajouter une ordonnance",
                onClick = {
                    when (state.step) {
                        0 -> navController.navigateUp()
                        2 -> viewModel.changeStep(0)
                        7 -> viewModel.changeStep(0)
                        else -> viewModel.previousPage()
                    }
                }
            )
        },
        BottomBar = { BottomBar(state.step) }
    ) {
        viewModel.PageFromStep(navController)
    }
}