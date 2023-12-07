package com.example.frontend_android.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontend_android.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun medicineCounter(numberOfMedicine : Int) {

    Text(text = "$numberOfMedicine médicaments")

}


@Preview(showBackground = true)
@Composable
fun previewMedicineCounterChips()
{
    medicineCounter(numberOfMedicine = 3)
}

