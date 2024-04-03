package com.example.frontend_android.ui.pages.notification.add_edit_notification

import DayCard
import android.Manifest
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.ui.components.forms.TimeInput
import com.example.frontend_android.ui.components.layout.BottomBarSaveOrDelete
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.theme.MedreminderTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun AddEditNotificationScreen(
    navController: NavController,
    viewModel: AddEditNotificationsViewModel = hiltViewModel(),
) {
    // >====================< notifications >====================<
    val permission = Manifest.permission.POST_NOTIFICATIONS
    val notificationPermissionState = rememberPermissionState(permission)
    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            //it's ok
        } else {
            ToastActivateNotification(context)
            Log.e("TXT", "permission denied ")

        }
    }

    LaunchedEffect(notificationPermissionState) {
        if (!notificationPermissionState.permissionRequested && notificationPermissionState.shouldShowRationale) {
            ToastActivateNotification(context)
        } else {
            requestPermissionLauncher.launch(permission)
        }
    }

    // >====================< View >====================<

    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var expanded by remember { mutableStateOf(false) }
    Log.e("ALARM", "stae prescription id : ${state.prescriptionId}")
    var selectedText by remember { mutableStateOf(state.prescriptionsList.find { it.id == state.prescriptionId }?.title ?: "") }

    var saveButtonIsEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(saveButtonIsEnabled) {
        if (saveButtonIsEnabled) return@LaunchedEffect
        else delay(1000L)

    }

    //observeur permettant de récupéré les evenement du viewModel
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is AddEditNotificationsViewModel.UiEvent.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message
                        )
                    }
                }

            }
        }
    }



    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        topBar = {
            TopBar(
                navController = navController,
                canGoBack = true,
                title = "Ajouter une alarme"
            )
        },
        bottomBar = {
            BottomBarSaveOrDelete(
                navController = navController,
                onValidation = { viewModel.onEvent(AddEditNotificationEvent.SaveNotification) },
                onDelete = { viewModel.onEvent(AddEditNotificationEvent.DeleteNotification) }
            )

        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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

                Spacer(modifier = Modifier.size(20.dp))

                Text("Sélectionner l'ordonnance associée :")

                Spacer(modifier = Modifier.size(10.dp))
                Log.e("ALARM", "STATE 1 PRESCRIPTION LIST : ${state.prescriptionsList}")

                Spacer(modifier = Modifier.size(20.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    OutlinedTextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        for (prescription in state.prescriptionsList) {
                            DropdownMenuItem(
                                text = { Text(text = prescription.title) },
                                onClick = {
                                    viewModel.onEvent(
                                        AddEditNotificationEvent.SelectPrescriptionToLink(
                                            prescription.id ?: -1L
                                        )
                                    )
                                    selectedText = prescription.title
                                    expanded = false
                                    Toast.makeText(context, prescription.title, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            )
                        }
                    }
                }

                Row() {
                    Text("Répété l'alarme tous les")
                    OutlinedTextField(value = "", onValueChange = {} )

                }
            }
        }
    }
}

fun ToastActivateNotification(context: Context) {
    Toast.makeText(
        context,
        "Veuillez activer les notifications pour utiliser cette fonctionnalité",
        Toast.LENGTH_LONG
    ).show()
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