package com.example.frontend_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.frontend_android.feature_prescription.presentation.util.screens.MainScreen
import com.example.frontend_android.ui.theme.FrontendandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FrontendandroidTheme {

            }
            MainScreen()
        }
    }
}

