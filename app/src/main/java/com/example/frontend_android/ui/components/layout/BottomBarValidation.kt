package com.example.frontend_android.ui.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomBarValidation (
    navController: NavController,
    onValidation: () -> Unit,
    onCancellation: () -> Unit,
) {

    BottomAppBar() {

        Button(
            modifier = Modifier.background(Color.Transparent),
            onClick = {
                onCancellation()
                navController.navigateUp()
            },
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = "Cancel",

                    // TODO
                    // tint = Red700
                )
                Text(text = "Annuler")
            }
        }

        Button(
            modifier = Modifier.background(Color.Transparent),
            onClick = {
                onValidation()
                navController.navigateUp()
            },
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done",
                    // TODO
                    // tint = Green600
                )
                Text(text = "Sauvegarder")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarValidationPreview() {
    BottomBarValidation(
        onValidation = { },
        onCancellation = { },
        navController = rememberNavController()
    )
}