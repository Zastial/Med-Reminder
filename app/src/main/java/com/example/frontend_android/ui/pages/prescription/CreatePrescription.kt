package com.example.frontend_android.pages.prescription

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarStepNavigation
import com.example.frontend_android.components.layout.TopBarPrescriptionNavigation
import com.example.frontend_android.pages.prescription.creation_pages.FillPrescriptionInfos
import com.example.frontend_android.ui.pages.prescription.creation_pages.ImportPrescriptionImage
import com.example.frontend_android.ui.components.layout.BottomBarValidation
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel

@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    @Composable
    fun PageFromStep(step: Int) {
        return when (step) {
            0 -> ImportPrescriptionImage(viewModel)
            1 -> FillPrescriptionInfos(viewModel)
            else -> {}
        }
    }

    @Composable
    fun BottomBar(step : Int) {
        when (step) {
            0 -> BottomBarStepNavigation(
                navController = navController,
                onClick = { viewModel.nextPage() },
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
        PageFromStep(state.step)
    }
}