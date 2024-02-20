package com.example.frontend_android.ui.pages.prescription

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.layout.BottomBarStepNavigation
import com.example.frontend_android.components.layout.TopBarPrescriptionNavigation
import com.example.frontend_android.ui.components.layout.BottomBarValidation
import com.example.frontend_android.ui.components.layout.BaseLayout

@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value

    fun textExtractionStep() {
        if (!Uri.EMPTY.equals(state.imageUri) && state.imageUri != null) {
            viewModel.getImageFromUri(context)
        }
        viewModel.nextPage()
    }

    @Composable
    fun BottomBar(step : Int) {
        when (step) {
            0 -> BottomBarStepNavigation(
                navController = navController,
                onClick = { textExtractionStep() },
            )
            6 -> BottomBarValidation(
                navController = navController,
                onValidation = { viewModel.insertPrescription() },
                onCancellation = { viewModel.previousPage() }
            )
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
                    if (state.step == 0) {
                        navController.navigateUp()
                    } else {
                        viewModel.previousPage()
                    }
                }
            )
        },
        BottomBar = { BottomBar(state.step) }
    ) {
        viewModel.PageFromStep()
    }
}