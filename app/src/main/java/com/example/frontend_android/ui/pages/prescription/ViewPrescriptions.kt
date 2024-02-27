package com.example.frontend_android.ui.pages.prescription

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.ui.components.cards.PrescriptionCard
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar


@Composable
fun ViewPrescriptions(
    navController: NavController,
    viewModel: PrescriptionsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Ordonnances",
                canGoBack = false
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController
            )
        }
    ) {
        Column {
            Button(onClick = { navController.navigate(Screen.createPrescription.route) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Plus")
                Text(text = "Ajouter une ordonnance")
            }

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(state.prescriptionsWithRelations) { prescriptionWithRelations -> PrescriptionCard(prescriptionWithRelations = prescriptionWithRelations) }
            }
        }
    }
}