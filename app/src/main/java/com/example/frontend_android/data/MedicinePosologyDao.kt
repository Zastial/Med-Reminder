package com.example.frontend_android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.frontend_android.data.model.Doctor
import com.example.frontend_android.data.model.MedicinePosology
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicinePosologyDao {

    @Query("SELECT * FROM MedicinePosology")
    fun getMedicinePosologies(): Flow<List<MedicinePosology>>

    @Query("SELECT * FROM MedicinePosology WHERE id = :id")
    suspend fun getMedicinePosology(id: Int): MedicinePosology?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicinePosology(medicine_posology: MedicinePosology): Long

    @Delete
    suspend fun deleteMedicinePosology(medicine_posology: MedicinePosology)

}