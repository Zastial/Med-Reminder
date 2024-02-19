package com.example.frontend_android.ui.pages.notification.notifications

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class NotificationsViewModel @Inject constructor(

) : ViewModel() {

    private val _state = mutableStateOf(NotificationState())
    val state: State<NotificationState> = _state



    fun onEvent(event : NotificationEvent) {

        when(event) {
            is NotificationEvent.ToggleAlarmState -> {

                event.value
            }

        }

    }
}