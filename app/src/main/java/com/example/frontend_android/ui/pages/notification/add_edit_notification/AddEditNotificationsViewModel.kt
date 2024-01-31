package com.example.frontend_android.ui.pages.notification.add_edit_notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.model.entities.InvalidAlarmException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class AddEditNotificationsViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(AddEditNotificationState())
    val state: StateFlow<AddEditNotificationState> = _state.asStateFlow()

    // permet de gérer les evenements
    // ex : quand on sauvegarde une alarme
    // on affiche un Toast
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event : AddEditNotificationEvent) {

        when(event){
            is AddEditNotificationEvent.EnteredHour -> {


            }
            is AddEditNotificationEvent.EnteredMinute -> {


            }



            is AddEditNotificationEvent.SaveNotification -> {
                viewModelScope.launch {
                    try {

                        // save alarm avec repo

                        emitEvent(UiEvent.SaveNotification)
                    } catch (e : InvalidAlarmException) {

                        emitEvent(
                            UiEvent.ShowToast(
                                message = e.message ?: "Impossible de sauvegarder l'alarme"
                            )
                        )
                    }

                }
            }
        }


        _state.update { currentState ->
                currentState.copy(

                )
        }
    }

    suspend fun emitEvent(event: UiEvent) {
        _eventFlow.emit(event)
    }


    sealed class UiEvent {
        data class ShowToast(val message: String): UiEvent()
        object SaveNotification: UiEvent()
    }
}