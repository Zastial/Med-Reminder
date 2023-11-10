package com.example.frontend_android.prescription.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.frontend_android.prescription.data.data_source.PrescriptionDao
import com.example.frontend_android.prescription.domain.repository.PrescriptionRepository
import com.example.frontend_android.prescription.domain.model.Prescription
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.util.Date

class PrescriptionRepositoryImpl(
    private val dao: PrescriptionDao
): PrescriptionRepository {

    override fun getPrescriptions(): Flow<List<Prescription>> {
        return dao.getPrescriptions()
    }

    override suspend fun getPrescriptionById(id: Int): Prescription? {
        return dao.getPrescriptionById(id)
    }

    override suspend fun insertPrescription(prescription: Prescription) {
        dao.insertPrescription(prescription)
    }

    override suspend fun deletePrescription(prescription: Prescription) {
        dao.deletePrescription(prescription)
    }


}