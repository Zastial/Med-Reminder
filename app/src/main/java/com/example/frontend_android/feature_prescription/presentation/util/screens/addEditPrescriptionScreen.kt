package com.example.frontend_android.feature_prescription.presentation.util.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.frontend_android.feature_prescription.domain.model.Prescription

@Composable
fun addEditPrescriptionScreen(
    id : String,
    onBack : () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Prescription with id : ${id} ")
        Button(onClick = { onBack() }) {
            Text(text = "Retour")
        }
    }
}