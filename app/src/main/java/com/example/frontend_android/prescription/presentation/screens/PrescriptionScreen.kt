package com.example.frontend_android.feature_prescription.presentation.util


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.frontend_android.R
import com.example.frontend_android.prescription.presentation.screens.PrescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun PrescriptionScreen(
    navController : NavHostController,
    showPrescription : () -> Unit,
    viewModel: PrescriptionViewModel = hiltViewModel()
    ) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            state.prescriptions.forEach { it ->
                Text(text = it.title)
            }
        }

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