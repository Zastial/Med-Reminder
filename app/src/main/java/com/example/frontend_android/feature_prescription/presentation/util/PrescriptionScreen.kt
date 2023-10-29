package com.example.frontend_android.feature_prescription.presentation.util


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController


@Composable
fun PrescriptionScreen(
    navController : NavHostController,
    entry: NavBackStackEntry
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val textRecup = entry.savedStateHandle.get<String>("myText")
        textRecup?.let {
            Text(text = textRecup)
        }


        Button(onClick = {
            navController.navigate(Screen.otherScreen.route)
        }) {
            Text(text = "Aller à l'écran 2")
        }
    }
}