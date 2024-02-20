package com.example.frontend_android.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.frontend_android.data.model.dao.AlarmDao
import com.example.frontend_android.data.model.dao.MedicinePosologyDao
import com.example.frontend_android.data.model.dao.PrescriptionDao
import com.example.frontend_android.data.model.dao.SideEffectDao
import com.example.frontend_android.data.model.entities.AlarmRecord
import com.example.frontend_android.data.model.entities.Doctor
import com.example.frontend_android.data.model.entities.MedicinePosology
import com.example.frontend_android.data.model.entities.Prescription
import com.example.frontend_android.data.model.entities.SideEffect
import com.example.frontend_android.utils.Converters

@Database(entities = [
        Prescription::class,
        MedicinePosology::class,
        SideEffect::class,
        AlarmRecord::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun prescriptionDao(): PrescriptionDao
    abstract fun medicinePosologyDao(): MedicinePosologyDao
    abstract fun sideEffectDao(): SideEffectDao
    abstract fun alarmDao(): AlarmDao

    companion object {
        const val DATABASE_NAME = "db"
    }
}
