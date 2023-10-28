package com.example.frontend_android

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.feature_prescription.presentation.PrescriptionsScreen
import com.example.frontend_android.feature_prescription.presentation.util.OtherScreen
import com.example.frontend_android.feature_prescription.presentation.util.Screen

// Définition du graphe de navigation entre les écrans
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.prescriptionScreen.route) {

        composable(route = Screen.prescriptionScreen.route) { entry ->
            val textRecup = entry.savedStateHandle.get<String>("myText")


            Column(
                modifier = Modifier.fillMaxSize()
            ) {


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

        composable(
            route = Screen.otherScreen.route
        ) {
            //OtherScreen(navController = navController)

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
    }
}