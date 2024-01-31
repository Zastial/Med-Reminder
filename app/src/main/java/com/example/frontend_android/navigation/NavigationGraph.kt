package com.example.frontend_android.navigation
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

// Point d'entree du graphe de navigation entre les écrans
@Composable
fun NavigationGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RootScreen.prescription.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        prescriptionGraph(navController = navController)
        notificationGraph(navController = navController)
        medicineGraph(navController = navController)
        userGraph(navController = navController)
    }
}

