package com.example.frontend_android.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarPrescriptionNavigation(navController: NavController, title: String, onClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        title = { Text(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, text=title) },
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).shadow(4.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPrescriptionNavigationPreview() {
    TopBarPrescriptionNavigation(
        navController = rememberNavController(),
        title = "Ajouter une ordonnance",
        onClick = {}
    )
}