package com.example.frontend_android.prescription.presentation.add_edit_prescription

sealed class AddEditPrescriptionEvent {
    object SavePrescription: AddEditPrescriptionEvent()
}