package com.example.frontend_android.ui.pages.notification.add_edit_notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
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
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
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
                    //showToast(message = event.message, duration = Toast.LENGTH_SHORT)
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
        bottomBar = { BtnContinue(actionText = "Sauvegarder",modifier = Modifier.padding(40.dp,0.dp,40.dp,20.dp), onClick = { viewModel.onEvent(AddEditNotificationEvent.SaveNotification) })}
    ) {

        Surface(modifier = Modifier
            .padding(it)) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                val timePickerState = rememberTimePickerState(Calendar.HOUR, initialMinute = Calendar.MINUTE,  is24Hour = true)
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