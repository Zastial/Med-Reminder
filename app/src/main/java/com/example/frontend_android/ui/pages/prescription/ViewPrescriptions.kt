package com.example.frontend_android.ui.pages.prescription

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.ui.components.cards.PrescriptionCard
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.components.ressourceNotFound.NotFound


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
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.prescriptionsWithRelations.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxHeight(0.90f)
                ) {
                    NotFound("Aucune ordonnance enregistrée.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.90f)
                ) {
                    items(state.prescriptionsWithRelations) { prescriptionWithRelations -> PrescriptionCard(prescriptionWithRelations = prescriptionWithRelations) }
                }
            }
            Button(
                modifier = Modifier.fillMaxHeight(),
                onClick = { navController.navigate(Screen.createPrescription.route) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Plus")
                Text(text = "Ajouter une ordonnance")
            }
        }
    }
}