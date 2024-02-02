package com.example.frontend_android.pages.prescription

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarStepNavigation
import com.example.frontend_android.components.layout.TopBarPrescriptionNavigation
import com.example.frontend_android.pages.prescription.creation_pages.FillPrescriptionInfos
import com.example.frontend_android.pages.prescription.creation_pages.ImportPrescriptionImage
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarValidation
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel
import com.example.frontend_android.utils.TextExtractionFromImage

@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value

    @Composable
    fun PageFromStep(step: Int) {
        return when (step) {
            0 -> ImportPrescriptionImage(viewModel)
            1 -> FillPrescriptionInfos(viewModel)
            else -> {}
        }
    }

    fun textExtractionStep() {
        if (viewModel.state.value.imageUri != null) {
            val textExtraction = TextExtractionFromImage()
            textExtraction.onTranslateButtonClick(context, viewModel)
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
            1 -> BottomBarStepNavigation(
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