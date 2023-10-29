package com.example.frontend_android


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.feature_prescription.presentation.util.OtherScreen
import com.example.frontend_android.feature_prescription.presentation.util.PrescriptionScreen
import com.example.frontend_android.feature_prescription.presentation.util.Screen

// Définition du graphe de navigation entre les écrans
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val bottomNavigationItem : List<Screen> = listOf(
        Screen.cameraScreen,
        Screen.prescriptionsScreen,
        Screen.alarmsScreen
    )

    Scaffold(bottomBar = {
            BottomAppBar() {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.prescriptionsScreen.route) {

                    composable(route = Screen.prescriptionsScreen.route) { entry ->
                        PrescriptionScreen(navController = navController , entry = entry)



                    }

                    composable(
                        route = Screen.otherScreen.route
                    ) {
                        OtherScreen(navController = navController)


                    }
                }



            }

        }) { padding ->
        val pad = padding

    }




    

}