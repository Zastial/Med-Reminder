package com.example.frontend_android.ui.pages.notification

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionState
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class NotificationsViewModel @Inject constructor(
) : ViewModel() {

    private val _state = mutableStateOf(())
    val state: State<> = _state



}