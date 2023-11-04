package com.example.frontend_android.feature_prescription.domain.repository

import com.example.frontend_android.feature_prescription.domain.model.Prescription
import kotlinx.coroutines.flow.Flow

interface PrescriptionRepository {

    fun getPrescription() : Flow<List<Prescription>>

    suspend fun getPrescriptionById(id: Int) : Prescription?

    suspend fun insertPrescription(prescription : Prescription)

    suspend fun deletePrescription(prescription : Prescription)
}