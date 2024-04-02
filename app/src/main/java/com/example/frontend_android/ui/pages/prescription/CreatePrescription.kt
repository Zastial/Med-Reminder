package com.example.frontend_android.ui.pages.prescription

import android.net.Uri
import android.util.Log
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
            5 -> BottomBarValidation(
                navController = navController,
                onValidation = {
                    viewModel.insertPrescription()
                    navController.navigateUp()
                },
                onCancellation = {
                    viewModel.previousPage()
                    navController.navigateUp()
                }
            )
            6 -> {}
            9 -> BottomBarValidation(
                navController = navController,
                onValidation = {
                    viewModel.deleteMedicineAssociated(Pair(viewModel.state.value.medicineClicked, viewModel.state.value.old_posology))
                    viewModel.addMedicineAssociated(Pair(viewModel.state.value.medicineClicked, viewModel.state.value.posology))
                    viewModel.changeStep(4)
                               },
                onCancellation = {
                    viewModel.changePosologyMedicine("")
                    viewModel.changeOldPosology("")
                    viewModel.changeStep(4)
                }
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
                        2 -> viewModel.changeStep(0)
                        6 -> viewModel.changeStep(0)
                        7 -> viewModel.changeStep(4)
                        9 -> {
                            viewModel.changePosologyMedicine("")
                            viewModel.changeStep(4)
                        }
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