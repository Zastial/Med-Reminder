package com.example.frontend_android.prescription.presentation.add_edit_prescription

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.prescription.domain.model.InvalidPrescriptionException
import com.example.frontend_android.prescription.domain.model.Prescription
import com.example.frontend_android.prescription.domain.use_case.PrescriptionUseCases
import com.example.frontend_android.prescription.presentation.prescriptions.PrescriptionEvent
import com.example.frontend_android.prescription.presentation.prescriptions.PrescriptionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddEditPrescriptionViewModel @Inject constructor(
    private val prescriptionUseCases: PrescriptionUseCases
): ViewModel() {

    fun onEvent(event: AddEditPrescriptionEvent) {
        when(event) {
            is AddEditPrescriptionEvent.SavePrescription -> {
                viewModelScope.launch {
                    try {
                        prescriptionUseCases.addPrescription(
                            Prescription(
                                id = null,
                                title = "Titre 2",
                                description = "Ceci est une description",
                                deliveredAt = LocalDate.now()
                            )
                        )
                    } catch (e: InvalidPrescriptionException) {}
                }
            }
        }
    }

}