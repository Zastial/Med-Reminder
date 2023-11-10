package com.example.frontend_android.navigation




import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*



// Point d'entree du graphe de navigation entre les écrans
@Composable
fun NavigationGraph(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = RootScreen.notification.route) {

        prescriptionGraph(navController = navController)
        notificationGraph(navController = navController)
        medicineGraph(navController = navController)
        userGraph(navController = navController)



    }
}

