package com.example.frontend_android.ui.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.ui.theme.md_theme_light_onPrimaryCancel
import com.example.frontend_android.ui.theme.md_theme_light_primaryCancel

@Composable
fun BottomBarValidation (
    navController: NavController,
    onValidation: () -> Unit,
    onCancellation: () -> Unit,
) {

    BottomAppBar(
        modifier = Modifier
            .height(70.dp),
        contentPadding = PaddingValues(16.dp, 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    onCancellation()
                    navController.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = md_theme_light_primaryCancel
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = "Cancel",
                    tint = md_theme_light_onPrimaryCancel
                )
            }

            Button(
                modifier = Modifier.background(Color.Transparent),
                onClick = {
                    onValidation()
                    navController.navigateUp()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done",
                )
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