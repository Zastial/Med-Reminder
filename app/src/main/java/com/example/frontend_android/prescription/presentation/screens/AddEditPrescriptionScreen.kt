package com.example.frontend_android.prescription.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddEditPrescriptionScreen(
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