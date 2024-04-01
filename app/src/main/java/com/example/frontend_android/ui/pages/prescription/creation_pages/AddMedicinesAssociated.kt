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
    var medicineAssociated = state.medecineAndDosage[state.medecineAndDosage.size-1]
    Log.d("test_cis2", medicineAssociated.toString())

    if (state.medecineAndDosage.size == null) {
        return;
    }

    val lines = listOf(
        "Administration: ",
        medicineAssociated.first.administration.replaceFirstChar { it.uppercase() },
        "Type de médicament: ",
        medicineAssociated.first.form.replaceFirstChar { it.uppercase() },
        "Nom générique",
        medicineAssociated.first.generName?.replaceFirstChar { it.uppercase() } ?: "Inconnu",
        "Dosage: ",
        medicineAssociated.first.dose,
        "Substance active: ",
        medicineAssociated.first.substanceName.replaceFirstChar { it.uppercase() }
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = medicineAssociated.first.name,
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
            value = medicineAssociated.second ?: "",
            onValueChange = { viewModel.changePosologyMedicineAdded(it) },
            label = {
                Text(text = "Saisissez votre posologie")
            }
        )
    }
}
