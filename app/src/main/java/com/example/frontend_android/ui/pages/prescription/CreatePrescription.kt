package com.example.frontend_android.ui.pages.prescription

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.TopBarPrescriptionNavigation
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarStepNavigation
import com.example.frontend_android.ui.components.layout.BottomBarValidation
import java.time.LocalDate

@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    fun returnToFirstPage() {
        viewModel.changeNom("")
        viewModel.changeDescription("")
        viewModel.changeDate(LocalDate.now())
        viewModel.changenomDocteur("")
        viewModel.changeEmailDocteur("")
        viewModel.changeStep(0)
    }

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
                        2 -> returnToFirstPage()
                        else -> viewModel.previousPage()
                    }
                }
            )
        },
        BottomBar = { BottomBar(state.step) }
    ) {
        viewModel.PageFromStep()
    }
}