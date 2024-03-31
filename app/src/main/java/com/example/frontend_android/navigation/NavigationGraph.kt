package com.example.frontend_android.navigation
import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

// Point d'entree du graphe de navigation entre les écrans
@Composable
fun NavigationGraph() {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_infos", Context.MODE_PRIVATE)
    val userInfos = sharedPreferences.all

    val navController = rememberNavController()

    val startDestination = if (userInfos["accepted_conditions"] != null) {
        RootScreen.prescription.route
    } else {
        RootScreen.welcome.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        welcomeGraph(navController = navController)
        prescriptionGraph(navController = navController)
        notificationGraph(navController = navController)
        medicineGraph(navController = navController)
        userGraph(navController = navController)
    }
}

