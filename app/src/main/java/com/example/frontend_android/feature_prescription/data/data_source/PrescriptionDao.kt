package com.example.frontend_android.feature_prescription.data.data_source

import com.example.frontend_android.feature_prescription.domain.model.Prescription

interface PrescriptionDao {

    fun gestPrescriptions() : List<Prescription>

    fun getPrescriptionById(id : Int) : Prescription?

    fun insertPrescription(prescription : Prescription)

    fun deletePrescription(prescription : Prescription)
}