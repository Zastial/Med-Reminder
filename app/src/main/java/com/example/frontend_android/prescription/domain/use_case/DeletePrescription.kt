package com.example.frontend_android.prescription.domain.use_case

import com.example.frontend_android.prescription.domain.model.Prescription
import com.example.frontend_android.prescription.domain.repository.PrescriptionRepository

class DeletePrescription(
    private val repository: PrescriptionRepository
) {
    suspend operator fun invoke(prescription: Prescription) {
        repository.deletePrescription(prescription)
    }

}