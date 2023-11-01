package com.example.frontend_android.feature_prescription.presentation.util


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController


@Composable
fun PrescriptionScreen(
    navController : NavHostController,
    showPrescription : () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Prescription")
        Button(
            onClick = { showPrescription() }
        ) {
            Text("Go to prescription with ID")
        }
    }
}