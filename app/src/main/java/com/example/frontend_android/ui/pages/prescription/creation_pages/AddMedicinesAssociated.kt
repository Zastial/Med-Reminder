package com.example.frontend_android.ui.pages.prescription.creation_pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.pages.medicines.MedicineInformationsViewModel
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel

@Composable
fun AddMedicinesAssociated(
    navController: NavController,
    viewModel: CreatePrescriptionViewModel,
) {

    val state = viewModel.state.value
    var medicineAssociated = state.medicineClicked

    val lines = listOf(
        "Administration: ",
        medicineAssociated.administration.replaceFirstChar { it.uppercase() },
        "Type de médicament: ",
        medicineAssociated.form.replaceFirstChar { it.uppercase() },
        "Nom générique",
        medicineAssociated.generName?.replaceFirstChar { it.uppercase() } ?: "Inconnu",
        "Dosage: ",
        medicineAssociated.dose,
        "Substance active: ",
        medicineAssociated.substanceName.replaceFirstChar { it.uppercase() }
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = medicineAssociated.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            modifier = Modifier.padding(24.dp, 8.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(lines.size) {
                val fontWeight =  if (it % 2 != 0) FontWeight.Bold else FontWeight.Normal
                Text(text = lines[it], fontWeight = fontWeight)
            }
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.posology ?: "",
            onValueChange = {
                viewModel.changePosologyMedicine(it) },
            label = {
                Text(text = "Saisissez votre posologie")
            }
        )
    }
}
