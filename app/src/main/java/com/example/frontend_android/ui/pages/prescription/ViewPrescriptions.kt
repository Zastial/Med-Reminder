package com.example.frontend_android.ui.pages.prescription

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.R
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.ui.components.cards.PrescriptionCard
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.components.ressourceNotFound.NotFound
import java.time.LocalTime
import kotlin.math.abs


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
            if (state.alarms.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.10f)
                        .fillMaxWidth(0.90f)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_ghost),
                            contentDescription = "ghost smiley",
                        )

                        Spacer(modifier = Modifier.size(16.dp))

                        Text(
                            text = "Pas d'alarme configurée.",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            } else {
                val now = LocalTime.now()
                val closestAlarm = state.alarms.filter { it.isScheduled }.minByOrNull { alarm ->
                    val alarmTime = LocalTime.of(alarm.hours, alarm.minutes)
                    val diff = abs(now.toSecondOfDay() - alarmTime.toSecondOfDay())
                    diff
                }
                val formattedTime = String.format("%02dh%02d", closestAlarm!!.hours, closestAlarm.minutes)

                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.10f)
                        .fillMaxWidth(0.90f)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_ghost_smile),
                            contentDescription = "happy smiley",
                        )

                        Spacer(modifier = Modifier.size(16.dp))

                        Text(
                            text = "Prochaine alarme à : $formattedTime",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

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
                    items(state.prescriptionsWithRelations) { prescriptionWithRelations ->
                        PrescriptionCard(
                            navController = navController,
                            prescriptionWithRelations = prescriptionWithRelations
                        )
                    }
                }
            }
            Button(
                modifier = Modifier.fillMaxHeight(0.90f),
                onClick = { navController.navigate(Screen.createPrescription.route) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Plus")
                Text(text = "Ajouter une ordonnance")
            }
        }
    }
}