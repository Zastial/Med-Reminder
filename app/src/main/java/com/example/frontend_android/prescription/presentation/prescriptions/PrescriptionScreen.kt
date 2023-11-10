package com.example.frontend_android.feature_prescription.presentation.util


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.frontend_android.common.navigation.Screen
import com.example.frontend_android.prescription.presentation.prescriptions.PrescriptionViewModel


@Composable
fun PrescriptionScreen(
    navController : NavHostController,
    showPrescription : () -> Unit,
    viewModel: PrescriptionViewModel = hiltViewModel()
    ) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = { navController.navigate(Screen.addEditPrescriptionScreen.route) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add icon")
                Text(text = "Ajouter une ordonnance")
            }

            Button(onClick = { showPrescription() }) {
                Text("Go to prescription with ID")
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.prescriptions) { prescription ->
                    Text(text = prescription.title)
                }

            }
        }

    }
}
