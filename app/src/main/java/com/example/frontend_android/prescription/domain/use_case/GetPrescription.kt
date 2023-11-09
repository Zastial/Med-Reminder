package com.example.frontend_android.prescription.domain.use_case

import com.example.frontend_android.prescription.domain.model.Prescription
import com.example.frontend_android.prescription.domain.repository.PrescriptionRepository

class GetPrescription (
    private val repository: PrescriptionRepository
) {

    suspend operator fun invoke(id: Int): Prescription? {
        return repository.getPrescriptionById(id);
    }

}