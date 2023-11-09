package com.example.frontend_android.prescription.data.data_source

import androidx.room.*
import com.example.frontend_android.prescription.domain.model.Prescription
import kotlinx.coroutines.flow.Flow


@Dao
interface PrescriptionDao {

    @Query("SELECT * FROM prescription")
    fun getPrescriptions() : Flow<List<Prescription>>

    @Query("SELECT * FROM prescription WHERE id = :id")
    fun getPrescriptionById(id : Int) : Prescription?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPrescription(prescription : Prescription)

    @Delete
    fun deletePrescription(prescription : Prescription)
}
