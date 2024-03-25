package com.example.frontend_android.ui.pages.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.model.dao.SideEffectDao
import com.example.frontend_android.data.model.entities.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class SideEffectsState (
    var isLoading: Boolean = true,
    val sideEffects: List<SideEffect> = emptyList()
)

@HiltViewModel
class SideEffectsHistoryViewModel  @Inject constructor (
    private val SideEffectsHistoryDao: SideEffectDao
): ViewModel() {

    private val _state = mutableStateOf(SideEffectsState())
    val state: State<SideEffectsState> = _state

    private var getsideEffectsJob: Job? = null

    init {
        retrieveSideEffects()
    }

    fun retrieveSideEffects() {
        getsideEffectsJob?.cancel()
        getsideEffectsJob = SideEffectsHistoryDao.getSideEffects()
            .onEach { sideEffects ->
                _state.value = state.value.copy(
                    sideEffects = sideEffects,
                )
                state.value.isLoading = false
            }
            .launchIn(viewModelScope)
    }
}