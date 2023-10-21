package com.example.frontend_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.feature_prescription.presentation.util.Screen
import com.example.frontend_android.ui.theme.FrontendandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendandroidTheme {

            }



            // Navigation et définition des routes pour naviguer entre les écrans
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.prescriptionScreen.route
            ) {
                //composable()
            }

        }
    }
}

