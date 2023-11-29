package com.example.frontend_android.pages.prescription

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.data.model.Prescription
import java.time.LocalDate

@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionModel = hiltViewModel()
) {

    Button(onClick = {
        viewModel.insertPrescription(Prescription(null, "Test 1", "Ceci est une description", LocalDate.now()))
    }) {
        Text(text = "Button")
    }
}