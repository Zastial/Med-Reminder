package com.example.frontend_android.prescription.domain.use_case

import com.example.frontend_android.prescription.domain.model.InvalidPrescriptionException
import com.example.frontend_android.prescription.domain.model.Prescription
import com.example.frontend_android.prescription.domain.repository.PrescriptionRepository

class AddPrescription(
    private val repository: PrescriptionRepository
) {

    @Throws(InvalidPrescriptionException::class)
    suspend operator fun invoke(prescription: Prescription) {
        if (prescription.title.isBlank()) {
            throw InvalidPrescriptionException("The title of the prescription cannot be empty.");
        }
        repository.insertPrescription(prescription);
    }

}