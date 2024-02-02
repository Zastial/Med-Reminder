package com.example.frontend_android.ui.pages.notification.add_edit_notification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.model.entities.InvalidAlarmException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class AddEditNotificationsViewModel @Inject constructor(

) : ViewModel() {

    private val _state = mutableStateOf(AddEditNotificationState())
    val state: State<AddEditNotificationState> = _state

    // permet de gérer les evenements
    // ex : quand on sauvegarde une alarme
    // on affiche un Toast
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event : AddEditNotificationEvent) {

        when(event){
            is AddEditNotificationEvent.EnteredHour -> {
                _state.value = state.value.copy(
                    hour = event.value
                )

            }
            is AddEditNotificationEvent.EnteredMinute -> {
                _state.value = state.value.copy(
                    hour = event.value
                )
            }



            is AddEditNotificationEvent.SaveNotification -> {
                viewModelScope.launch {
                    try {

                        // save alarm avec repo

                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = "Impossible de sauvegarder l'alarme"
                            )
                        )
                        //_eventFlow.emit(UiEvent.SaveNotification)
                    } catch (e : InvalidAlarmException) {

                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Impossible de sauvegarder l'alarme"
                            )
                        )
                    }

                }
            }
        }



    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveNotification: UiEvent()
    }
}