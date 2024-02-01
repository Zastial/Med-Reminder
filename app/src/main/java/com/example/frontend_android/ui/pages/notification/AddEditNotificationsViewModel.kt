package com.example.frontend_android.ui.pages.notification

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.frontend_android.data.model.entities.Alarm
import com.example.frontend_android.data.model.entities.defaultAlarm
import javax.inject.Inject

data class AddEditNotificationState (
    val alarm: Alarm = defaultAlarm
)
class AddEditNotificationsViewModel @Inject constructor(

) : ViewModel() {

    private val _state = mutableStateOf(AddEditNotificationState())
    val state: State<AddEditNotificationState> = _state



}