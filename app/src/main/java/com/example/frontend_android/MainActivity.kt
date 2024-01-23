package com.example.frontend_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.frontend_android.components.bottomSheets.bottomSheetOperationValidation
import com.example.frontend_android.components.buttons.btnContinue
import com.example.frontend_android.ui.theme.MedreminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedreminderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //NavigationGraph()
                    bottomSheetOperationValidation(
                        isSuccesfull = true,
                        isOpen = true,
                        title = "Votre ordonnance à été correctement enregistrée",
                        description = "L'opération c'est terminée avec succès",
                         actionButton = {
                            btnContinue(
                                enabled = true,
                                actionText = "Continue"
                            )
                        }
                    )
                }


            }
        }
    }
}

