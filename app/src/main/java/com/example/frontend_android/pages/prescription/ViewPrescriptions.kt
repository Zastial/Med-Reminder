package com.example.frontend_android.pages.prescription

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.components.cards.PrescriptionCard


@Composable
fun ViewPrescriptions(
    navController: NavController,
    viewModel: ViewPrescriptionsModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            Button(onClick = { navController.navigate(Screen.createPrescription.route) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Plus")
                Text(text = "Ajouter une ordonnance")
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.prescriptions) { prescription -> PrescriptionCard(prescription = prescription) }
            }

        }


    }



    

}