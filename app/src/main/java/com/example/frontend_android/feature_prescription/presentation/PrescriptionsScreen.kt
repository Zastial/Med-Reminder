package com.example.frontend_android.feature_prescription.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionsScreen() {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // launch instance
            },
                Modifier.background(color = MaterialTheme.colorScheme.primary)

            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Prescription" )
            }
        }

    ) { paddingValues -> 
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues.calculateBottomPadding())
            .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Bottom app padding : $paddingValues")

            repeat(50) {
                Text(it.toString())
            }
        }

    }

}