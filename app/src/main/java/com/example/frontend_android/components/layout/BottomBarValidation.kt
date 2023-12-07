package com.example.frontend_android.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.navigation.RootScreen
import com.example.frontend_android.ui.theme.Green600
import com.example.frontend_android.ui.theme.Red700
import com.example.frontend_android.ui.theme.White

@Composable
fun BottomBarValidation (
    onValidation: () -> Unit,
    onCancellation: () -> Unit,
    navController: NavController
) {

    Row(
        modifier = Modifier.padding(4.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {

        IconButton(onClick = {
            onCancellation()
            navController.navigateUp()
        }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = "Cancel",
                    tint = Red700
                )
                Text(text = "Annuler")
            }
        }

        IconButton(onClick = {
            onValidation()
            navController.navigateUp()
        }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done",
                    tint = Green600
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