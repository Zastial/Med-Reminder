package com.example.frontend_android.pages.prescription

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.data.model.Prescription
import com.example.frontend_android.prescription.component.PrescriptionItem.PrescriptionCard
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionModel = hiltViewModel()
) {


    var p = Prescription(null, "title", "desc", LocalDate.now(), 0)
    PrescriptionCard(prescription = )


}