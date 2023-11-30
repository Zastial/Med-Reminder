package com.example.frontend_android.pages.medicines

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.frontend_android.navigation.Screen

@Composable
fun ViewMedicines(
    navController: NavController,
    viewModel: ViewMedicinesModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "Page médicaments")


            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.medicines) { medicine -> Text(text = medicine.name) }
            }
        }


    }


}