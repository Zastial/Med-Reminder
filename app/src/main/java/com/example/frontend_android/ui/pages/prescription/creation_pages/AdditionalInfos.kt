package com.example.frontend_android.ui.pages.prescription.creation_pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel


@Composable
fun FillAdditionalInfos(viewModel: CreatePrescriptionViewModel) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = viewModel.stepToProgress(),
            modifier = Modifier.fillMaxWidth(),
            trackColor = MaterialTheme.colorScheme.tertiary
        )

        Text(
            text = "Informations supplémentaires",
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        )

        Column {
            Text(
                text = "Informations Docteur",
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 20.dp),
                value = state.nomDocteur,
                onValueChange = { viewModel.changenomDocteur(it) },
                label = {
                    Text(text = "Nom")
                },
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.emailDocteur,
                onValueChange = { viewModel.changeEmailDocteur(it) },
                label = {
                    Text(text = "Email")
                },
            )
        }
    }
}