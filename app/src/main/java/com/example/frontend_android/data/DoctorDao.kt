package com.example.frontend_android.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.frontend_android.data.model.Doctor
import kotlinx.coroutines.flow.Flow

@Dao
interface DoctorDao {

    @Query("SELECT * FROM Doctor")
    fun getDoctors(): Flow<List<Doctor>>

    @Query("SELECT * FROM Doctor WHERE id = :id")
    suspend fun getDoctor(id: Int): Doctor?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctor(doctor: Doctor): Long

    @Delete
    suspend fun deleteDoctor(doctor: Doctor)

}