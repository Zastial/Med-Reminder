package com.example.frontend_android.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

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

