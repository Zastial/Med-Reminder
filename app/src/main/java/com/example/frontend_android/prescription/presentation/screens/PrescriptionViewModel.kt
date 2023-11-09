package com.example.frontend_android.prescription.presentation.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.prescription.domain.use_case.PrescriptionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PrescriptionViewModel @Inject constructor (
    private val prescriptionUseCases: PrescriptionUseCases,
): ViewModel() {

    private val _state = mutableStateOf(PrescriptionState())
    val state: State<PrescriptionState> = _state

    private var getPrescriptionsJob: Job? = null

    init {
        getPrescriptions()
    }

    private fun getPrescriptions() {
        getPrescriptionsJob?.cancel()
        getPrescriptionsJob = prescriptionUseCases.getPrescriptions()
            .onEach { prescriptions ->
                _state.value = state.value.copy(
                    prescriptions = prescriptions,
                )
            }
            .launchIn(viewModelScope)
    }


}