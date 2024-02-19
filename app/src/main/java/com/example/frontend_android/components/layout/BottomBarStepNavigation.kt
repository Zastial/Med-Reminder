package com.example.frontend_android.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomBarStepNavigation (
    navController: NavController,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                onClick()
            },
            modifier = Modifier
                .width(300.dp)
                .padding(6.dp),
        ) {
            Text(text = "Continuer")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarStepNavigationPreview() {
    BottomBarStepNavigation(
        onClick = { },
        navController = rememberNavController(),
    )
}