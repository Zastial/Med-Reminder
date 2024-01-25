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
import com.example.frontend_android.components.layout.BottomBarValidation
import com.example.frontend_android.components.layout.TopBar
import com.example.frontend_android.layout.BaseLayout
import com.example.frontend_android.pages.prescription.creation_pages.ImportPrescriptionImage

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
            else -> {}
        }
    }

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
        PageFromStep(state.step)
    }
}