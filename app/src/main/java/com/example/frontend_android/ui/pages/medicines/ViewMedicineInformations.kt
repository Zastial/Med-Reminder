package com.example.frontend_android.ui.pages.medicines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar

@Composable
fun ViewMedicineInformations(
    navController: NavController,
    viewModel: MedicineInformationsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    if (state.medicine == null) {
        return;
    }

    val lines = listOf(
       "Administration: ",
        state.medicine.administration.replaceFirstChar { it.uppercase() },
        "Type de médicament: ",
        state.medicine.form.replaceFirstChar { it.uppercase() },
        "Prix: ",
        if (state.medicine.price != null) "${state.medicine.price}€" else "Inconnu",
        "Nom générique",
        state.medicine.generName?.replaceFirstChar { it.uppercase() } ?: "Inconnu",
        "Dosage: ",
        state.medicine.dose,
        "Substance active: ",
        state.medicine.substanceName.replaceFirstChar { it.uppercase() }
    )

    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Médicaments",
                canGoBack = true
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController,
            )
        }
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = state.medicine.name,
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
        }
    }
}
