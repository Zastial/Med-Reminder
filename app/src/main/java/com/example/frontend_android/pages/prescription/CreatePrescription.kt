package com.example.frontend_android.pages.prescription

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.frontend_android.data.model.Prescription
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePrescriptions(
    navController: NavController,
    viewModel: CreatePrescriptionModel = hiltViewModel()
) {




}