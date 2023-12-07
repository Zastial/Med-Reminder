package com.example.frontend_android.pages.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.PrescriptionDao
import com.example.frontend_android.data.model.Prescription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class PrescriptionsState (
    val prescriptions: List<Prescription> = emptyList()
)

@HiltViewModel
class ViewSideEffectsHistoryModel  @Inject constructor (
    private val SideEffectsHistoryDao: SideEffectsHistoryDao
): ViewModel() {

    private val _state = mutableStateOf(PrescriptionsState())
    val state: State<PrescriptionsState> = _state

    private var getPrescriptionsJob: Job? = null

    init {
        retrievePrescriptions()
    }

    private fun retrievePrescriptions() {
        getPrescriptionsJob?.cancel()
        getPrescriptionsJob = SideEffectsHistoryDao.getPrescriptions()
            .onEach { prescriptions ->
                _state.value = state.value.copy(
                    prescriptions = prescriptions,
                )
            }
            .launchIn(viewModelScope)
    }
}