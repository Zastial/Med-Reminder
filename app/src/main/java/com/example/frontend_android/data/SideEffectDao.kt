package com.example.frontend_android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.frontend_android.data.model.SideEffect
import com.example.frontend_android.data.model.relations.SideEffectWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface SideEffectDao {

    @Query("SELECT * FROM SideEffect")
    fun getSideEffects(): Flow<List<SideEffect>>

    @Query("SELECT * FROM SideEffect WHERE id = :id")
    suspend fun getSideEffect(id: Int): SideEffect?

    @Transaction
    @Query("SELECT * FROM SideEffect WHERE id = :id")
    suspend fun getSideEffectAndPrescription(id: Int): List<SideEffectWithRelations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSideEffect(side_effect: SideEffect): Long

    @Delete
    suspend fun deleteSideEffect(side_effect: SideEffect)

}