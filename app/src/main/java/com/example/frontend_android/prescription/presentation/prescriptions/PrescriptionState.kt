package com.example.frontend_android.prescription.presentation.prescriptions

import com.example.frontend_android.prescription.domain.model.Prescription

data class PrescriptionState (
    val prescriptions: List<Prescription> = emptyList(),
)