package com.example.frontend_android.prescription.domain.repository

import com.example.frontend_android.prescription.domain.model.Prescription
import kotlinx.coroutines.flow.Flow

interface PrescriptionRepository {
    fun getPrescriptions() : Flow<List<Prescription>>

    suspend fun getPrescriptionById(id: Int) : Prescription?

    suspend fun insertPrescription(prescription : Prescription)

    suspend fun deletePrescription(prescription : Prescription)
}