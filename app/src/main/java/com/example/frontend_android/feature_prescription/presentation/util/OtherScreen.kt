package com.example.frontend_android.feature_prescription.presentation.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var text by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = text ,
            onValueChange = { text = it},
            modifier = Modifier.width(300.dp)
        )


        Button(onClick = {
            // passe le text à l'écran précédent
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("myText", text)

            //retour à l'écran précédent en supprimant l'écran courant de la stack
            navController.popBackStack()

        }) {
            Text(text = "Aller à l'écran 1")
        }
    }
}