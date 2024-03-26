package com.example.frontend_android.ui.pages.prescription.creation_pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.navigation.NavController
import com.example.frontend_android.R
import com.example.frontend_android.data.model.entities.Medicine
import com.example.frontend_android.ui.components.cards.MedicineCard
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel

@Composable
fun MedicinesAssociated(navController: NavController, viewModel: CreatePrescriptionViewModel) {
    val userInfos = viewModel.sharedPreferences.all
    //var userAllergies = userInfos["allergies"] as Set<String>
    //Log.d("test", "TEST $userAllergies")
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = viewModel.stepToProgress(),
            modifier = Modifier.fillMaxWidth(),
            trackColor = MaterialTheme.colorScheme.tertiary
        )

        Text(
            text = "Médicaments associés",
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.ic_warning),
                contentDescription = "Description",
                tint = Color.Red
            )
            Spacer(modifier = Modifier.width(width = 10.dp))
            Text(
                text = "Vous avez ... médicament(s) en contre indication avec vos allergies",
                textAlign = TextAlign.Left,
            )
        }

        LazyColumn(
            modifier = Modifier.padding(16.dp, 0.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            items(viewModel.state.value.medecineAndDosage) {
                    medicine ->
                MedicineCard(navController = navController, medicine = medicine.first, true)
            }
        }
    }
}