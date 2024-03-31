package com.example.frontend_android.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.frontend_android.data.model.entities.Prescription
import com.example.frontend_android.data.model.relations.PrescriptionWithRelations
import kotlinx.coroutines.flow.Flow


@Dao
interface PrescriptionDao {
    @Query("SELECT * FROM Prescription")
    fun getPrescriptions() : Flow<List<Prescription>>

    @Query("SELECT * FROM Prescription WHERE id = :id")
    suspend fun getPrescription(id : Long) : PrescriptionWithRelations?

    @Transaction
    @Query("SELECT * FROM Prescription")
    fun getPrescriptionsWithRelations(): Flow<List<PrescriptionWithRelations>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrescription(prescription : Prescription): Long

    @Delete
    suspend fun deletePrescription(prescription : Prescription)
}
