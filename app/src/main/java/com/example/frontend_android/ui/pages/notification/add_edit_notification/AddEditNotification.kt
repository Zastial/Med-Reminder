package com.example.frontend_android.ui.pages.notification.add_edit_notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.ui.components.buttons.BtnContinue
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.theme.MedreminderTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditNotificationScreen(
    navController: NavController,
    viewModel: AddEditNotificationsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value




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
        topBar = { TopBar(navController= navController, canGoBack = true, title = "Ajouter une alarme")},
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalArrangement = Arrangement.Center,
            ) {

                TextField(
                    modifier = Modifier.width(100.dp),
                    value = state.hour,
                    placeholder = { Text(text="00") },
                    onValueChange = { viewModel.onEvent(AddEditNotificationEvent.EnteredHour(it))}
                )

                Text(text = ":")

                TextField(
                    modifier = Modifier.width(100.dp),
                    value = state.minutes,
                    placeholder = { Text(text="00") },
                    onValueChange = { viewModel.onEvent(AddEditNotificationEvent.EnteredMinute(it))}
                )
            }

            BtnContinue(
                actionText = "Sauvegarder",
                onClick = { viewModel.onEvent(AddEditNotificationEvent.SaveNotification) }
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