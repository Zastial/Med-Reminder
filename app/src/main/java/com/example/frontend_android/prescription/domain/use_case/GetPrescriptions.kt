package com.example.frontend_android.prescription.domain.use_case

import com.example.frontend_android.prescription.domain.model.Prescription
import com.example.frontend_android.prescription.domain.repository.PrescriptionRepository
import kotlinx.coroutines.flow.Flow

class GetPrescriptions(
    private val repository: PrescriptionRepository
) {

    operator fun invoke(): Flow<List<Prescription>> {
        /**
         * C'est ici qu'on va inclure un filtre ou un sorting si on veut
         */
        return repository.getPrescriptions();
    }

}