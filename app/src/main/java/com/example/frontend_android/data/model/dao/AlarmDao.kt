package com.example.frontend_android.data.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.frontend_android.data.model.entities.AlarmRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Query("SELECT * FROM Alarm")
    fun getAllAlarms(): Flow<List<AlarmRecord>>

    //@Query("SELECT * FROM Alarm WHERE id = :id")
    //suspend fun getAlarmById(id: Long): AlarmRecord?

    //@Transaction
    //@Query("SELECT * FROM Alarm WHERE id = :id")
    //suspend fun getAlarmAndPrescription(id: Int): List<AlarmWithRelations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlarm(alarmRecord: AlarmRecord): Long

    @Delete
    suspend fun deleteAlarm(alarmRecord: AlarmRecord)
}