package com.example.frontend_android.pages.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.PrescriptionDao
import com.example.frontend_android.data.SideEffectDao
import com.example.frontend_android.data.model.Prescription
import com.example.frontend_android.data.model.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class SideEffectsState (
    val sideEffects: List<SideEffect> = emptyList()
)

@HiltViewModel
class ViewSideEffectsHistoryModel  @Inject constructor (
    private val SideEffectsHistoryDao: SideEffectDao
): ViewModel() {

    private val _state = mutableStateOf(SideEffectsState())
    val state: State<SideEffectsState> = _state

    private var getsideEffectsJob: Job? = null

    init {
        retrieveSideEffects()
    }

    private fun retrieveSideEffects() {
        getsideEffectsJob?.cancel()
        getsideEffectsJob = SideEffectsHistoryDao.getSideEffects()
            .onEach { prescriptions ->
                _state.value = state.value.copy(
                    sideEffects = prescriptions,
                )
            }
            .launchIn(viewModelScope)
    }
}