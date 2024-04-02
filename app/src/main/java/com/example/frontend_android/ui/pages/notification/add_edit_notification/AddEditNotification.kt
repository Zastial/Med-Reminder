package com.example.frontend_android.ui.pages.notification.add_edit_notification

import DayCard
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.ui.components.forms.BtnContinue
import com.example.frontend_android.ui.components.forms.TimeInput
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.theme.MedreminderTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNotificationScreen(
    navController: NavController,
    viewModel: AddEditNotificationsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Log.e("ALARM screen state recieve", state.toString())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var enabled by remember { mutableStateOf(true) }

    LaunchedEffect(enabled) {
        if (enabled) return@LaunchedEffect
        else delay(1000L)

    }

    //observeur permettant de récupéré les evenement du viewModel
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when(event) {
                is AddEditNotificationsViewModel.UiEvent.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message
                        )
                    }
                }
                is AddEditNotificationsViewModel.UiEvent.SaveNotification -> {
                    scope.launch {
                        navController.navigateUp()
                    }

                }

            }
        }
    }



    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        topBar = {
            TopBar(
                navController= navController,
                canGoBack = true,
                title = "Ajouter une alarme"
            )},
        bottomBar = {
            BtnContinue(
                actionText = "Sauvegarder",
                modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 20.dp),
                enabled = enabled,
                onClick = {
                    enabled = false
                    viewModel.onEvent(
                        AddEditNotificationEvent.SaveNotification
                    )
                }
            )},
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)  }
    ) { it ->

        Surface(
            modifier = Modifier
            .padding(it)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Content of screen
                TimeInput(
                    hours = state.hours,
                    minutes = state.minutes,
                    setHours = { viewModel.onEvent(AddEditNotificationEvent.EnteredHour(it)) },
                    setMinutes = { viewModel.onEvent(AddEditNotificationEvent.EnteredMinute(it)) }
                )
                Spacer(Modifier.size(15.dp))

                //en attente de mieux
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                  DayCard(
                      day = DaysOfWeek.LUNDI,
                      onClick = {
                          viewModel.onEvent(
                              AddEditNotificationEvent.SelectDayToSchedule(
                                  DaysOfWeek.LUNDI
                              )
                          )
                      },
                      isActive = state.scheduledDays.contains(DaysOfWeek.LUNDI)
                  )
                    DayCard(
                        day = DaysOfWeek.MARDI,
                        onClick = {
                            viewModel.onEvent(
                                AddEditNotificationEvent.SelectDayToSchedule(
                                    DaysOfWeek.MARDI
                                )
                            )
                        },
                        isActive = state.scheduledDays.contains(DaysOfWeek.MARDI)
                    )
                    DayCard(
                        day = DaysOfWeek.MERCREDI,
                        onClick = {
                            viewModel.onEvent(
                                AddEditNotificationEvent.SelectDayToSchedule(
                                    DaysOfWeek.MERCREDI
                                )
                            )
                        },
                        isActive = state.scheduledDays.contains(DaysOfWeek.MERCREDI)
                    )
                    DayCard(
                        day = DaysOfWeek.JEUDI,
                        onClick = {
                            viewModel.onEvent(
                                AddEditNotificationEvent.SelectDayToSchedule(
                                    DaysOfWeek.JEUDI
                                )
                            )
                        },
                        isActive = state.scheduledDays.contains(DaysOfWeek.JEUDI)
                    )
                    DayCard(
                        day = DaysOfWeek.VENDREDI,
                        onClick = {
                            viewModel.onEvent(
                                AddEditNotificationEvent.SelectDayToSchedule(
                                    DaysOfWeek.VENDREDI
                                )
                            )
                        },
                        isActive = state.scheduledDays.contains(DaysOfWeek.VENDREDI)
                    )
                    DayCard(
                        day = DaysOfWeek.SAMEDI,
                        onClick = {
                            viewModel.onEvent(
                                AddEditNotificationEvent.SelectDayToSchedule(
                                    DaysOfWeek.SAMEDI
                                )
                            )
                        },
                        isActive = state.scheduledDays.contains(DaysOfWeek.SAMEDI)
                    )
                    DayCard(
                        day = DaysOfWeek.DIMANCHE,
                        onClick = {
                            viewModel.onEvent(
                                AddEditNotificationEvent.SelectDayToSchedule(
                                    DaysOfWeek.DIMANCHE
                                )
                            )
                        },
                        isActive = state.scheduledDays.contains(DaysOfWeek.DIMANCHE)
                    )
                }

            }


        }


    }

}


@Composable
@Preview
fun CreateNotificationScreenPreview() {
    MedreminderTheme {
        AddEditNotificationScreen(
            navController = rememberNavController(),
        )
    }
}