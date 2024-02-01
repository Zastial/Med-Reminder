package com.example.frontend_android.ui.pages.notification.add_edit_notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.theme.MedreminderTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditNotificationScreen(
    navController: NavController,
    viewModel: AddEditNotificationsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val scaffoldState = rememberScaffoldState()


    // observeur permettant de récupéré les evenement du viewModel
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditNotificationsViewModel.UiEvent.ShowToast -> {

                }
                is AddEditNotificationsViewModel.UiEvent.SaveNotification -> {
                    navController.navigateUp()
                }

            }
        }
    }



    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(navController= navController, canGoBack = true, title = "Ajouter une alarme") },
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            BasicTextField(
                value = state.hour,
                onValueChange = { viewModel.onEvent(AddEditNotificationEvent.EnteredHour(it))}
            )

            BasicTextField(
                value = state.minutes,
                onValueChange = { viewModel.onEvent(AddEditNotificationEvent.EnteredMinute(it))}
            )
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