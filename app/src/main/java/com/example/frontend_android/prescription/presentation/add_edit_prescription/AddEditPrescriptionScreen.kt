package com.example.frontend_android.prescription.presentation.add_edit_prescription

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddEditPrescriptionScreen(
    viewModel: AddEditPrescriptionViewModel = hiltViewModel()
) {


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { viewModel.onEvent(AddEditPrescriptionEvent.SavePrescription) }) {
            Icon(imageVector = Icons.Rounded.Save, contentDescription = "Save" )
            Text(text = "Sauvegarder")
        }
    }
}