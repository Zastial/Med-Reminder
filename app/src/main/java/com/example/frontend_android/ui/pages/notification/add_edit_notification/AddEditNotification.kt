package com.example.frontend_android.ui.pages.notification.add_edit_notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.ui.components.forms.BtnContinue
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.theme.MedreminderTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNotificationScreen(
    navController: NavController,
    viewModel: AddEditNotificationsViewModel = hiltViewModel()

) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val timePickerState = rememberTimePickerState(
        initialHour =  state.hours,
        initialMinute = state.minutes,
        is24Hour = true
    )




    // observeur permettant de récupéré les evenement du viewModel
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditNotificationsViewModel.UiEvent.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message
                        )
                    }
                }
                is AddEditNotificationsViewModel.UiEvent.SaveNotification -> {
                    navController.navigateUp()
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
                onClick = {
                    viewModel.onEvent(
                        AddEditNotificationEvent.SaveNotification(timePickerState.hour, timePickerState.minute)
                    )
                }
            )},
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)  }
    ) {

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
                TimeInput(state = timePickerState)
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