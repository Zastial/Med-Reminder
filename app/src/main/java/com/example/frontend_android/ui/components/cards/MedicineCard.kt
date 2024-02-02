package com.example.frontend_android.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.data.model.entities.Medicine
import com.example.frontend_android.ui.theme.Black
import com.example.frontend_android.ui.theme.Grey100
import com.example.frontend_android.ui.theme.Orange400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineCard(navController: NavController, medicine: Medicine, warningMessage: String? = null) {

    val textColor = if (warningMessage != null) Orange400 else Black

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface

        ),
        onClick = { navController.navigate("medicine_informations_screen/${medicine.cis}") }

    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Surface(modifier = Modifier.width(30.dp)) {
                if (warningMessage != null) {
                    Icon(imageVector = Icons.Outlined.Warning, contentDescription = "Warning", tint = Orange400)
                }
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {

                Text(
                    text = medicine.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = textColor
                )

                Text(text =  "Administration ${medicine.administration}", style = MaterialTheme.typography.labelMedium, color = Grey100)

            }

            Surface(modifier = Modifier.width(30.dp)) {
                Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "Show medicine")
            }

        }
    }

}

@Preview
@Composable
fun MedicineCardPreview() {
    MedicineCard(
        navController = rememberNavController(), 
        medicine = Medicine(
            cis = 61266250,
            cip7 = 3000147,
            cip13 = 3400930001479,
            administration = "cutanée",
            form = "pommade",
            name = "A 313 200 000 UI POUR CENT, pommade",
            price = "1,61",
            dose = "20mg",
            substanceName = "PHOSPHATE DE CODÉINE HÉMIHYDRATÉ"
        ),
    )
}

@Preview
@Composable
fun MedicineCardPreviewWithWarningMessage() {
    MedicineCard(
        navController = rememberNavController(),
        medicine = Medicine(
            cis = 61266250,
            cip7 = 3000147,
            cip13 = 3400930001479,
            administration = "cutanée",
            form = "pommade",
            name = "A 313 200 000 UI POUR CENT, pommade",
            price = "1,61",
            dose = "20mg",
            substanceName = "PHOSPHATE DE CODÉINE HÉMIHYDRATÉ"
        ),
        warningMessage = "Attention wola"
    )
}