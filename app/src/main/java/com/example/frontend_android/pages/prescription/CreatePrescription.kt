package com.example.frontend_android.pages.prescription

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarValidation
import com.example.frontend_android.components.layout.TopBar
import com.example.frontend_android.layout.BaseLayout
import com.example.frontend_android.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionModel = hiltViewModel()
) {

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            navController.currentBackStackEntry?.savedStateHandle?.set("uri", uri)
            navController.navigate(Screen.modifyPrescription.route)
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
        Button(onClick = { galleryLauncher.launch("image/*") }) {
            Text(text = "Upload picture")
        }
    }
}