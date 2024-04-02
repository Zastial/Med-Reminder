package com.example.frontend_android.ui.components.cards

import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.data.model.entities.Medicine
import com.example.frontend_android.ui.theme.md_theme_common_primaryWarning

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineCard(medicine: Medicine, hasWarning: Boolean = false, hasDelete : Boolean = false, onDelete : () -> Unit, onClick : () -> Unit, posology: String = "") {
    var borderStroke : BorderStroke
    if (hasWarning){
        borderStroke = BorderStroke(2.dp, md_theme_common_primaryWarning)
    }else{
        borderStroke = BorderStroke(0.dp, Color.Transparent)
    }
    Card(
        modifier = Modifier.padding(6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = { onClick() },
        border = borderStroke
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Surface(modifier = Modifier.width(30.dp)) {
                if (hasDelete){
                    IconButton(
                        onClick = {onDelete()},
                        content = {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete",
                                tint= md_theme_common_primaryDelete
                            )
                        }
                    )
                }
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {

                Text(
                    text = medicine.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if(posology != ""){
                    Text(text = posology, style = MaterialTheme.typography.labelMedium)
                }else{
                    Text(text = "Administration ${medicine.administration}", style = MaterialTheme.typography.labelMedium)
                }

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
        onDelete = {},
        onClick = {},
    )
}

@Preview
@Composable
fun MedicineCardPreviewWithWarningMessage() {
    MedicineCard(
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
        hasWarning = true,
        onDelete = {},
        onClick = {}
    )
}