package com.example.frontend_android.pages.prescription

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarStepNavigation
import com.example.frontend_android.components.layout.TopBarPrescriptionNavigation
import com.example.frontend_android.pages.prescription.creation_pages.FillPrescriptionInfos
import com.example.frontend_android.pages.prescription.creation_pages.ImportPrescriptionImage
import com.example.frontend_android.ui.components.layout.BottomBarValidation
import com.example.frontend_android.ui.layout.BaseLayout
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionModel

@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionModel = hiltViewModel()
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