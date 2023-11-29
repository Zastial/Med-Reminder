package com.example.frontend_android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.frontend_android.data.model.Prescription
import kotlinx.coroutines.flow.Flow


@Dao
interface PrescriptionDao {
    @Query("SELECT * FROM prescription")
    fun getPrescriptions() : Flow<List<Prescription>>

    @Query("SELECT * FROM prescription WHERE id = :id")
    suspend fun getPrescription(id : Int) : Prescription?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prescription : Prescription)

    @Delete
    suspend fun delete(prescription : Prescription)
}
