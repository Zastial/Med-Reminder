package com.example.frontend_android.feature_prescription.presentation.util


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import androidx.navigation.NavHostController
import com.example.frontend_android.R


@Composable
fun PrescriptionScreen(
    navController : NavHostController,
    showPrescription : () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Text(text = "Header")
        }

        item {
            Button(onClick = {

            }) {
                Text( text = stringResource(id = R.string.add_prescription))
            }
        }


        item {
            Button(
                onClick = { showPrescription() }
            ) {
                Text("Go to prescription with ID")
            }
        }


    }
}