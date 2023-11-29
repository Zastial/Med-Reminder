package com.example.frontend_android.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.frontend_android.data.model.Prescription
import com.example.frontend_android.utils.Converters

@Database(entities = [Prescription::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun prescriptionDao(): PrescriptionDao

    companion object {
        const val DATABASE_NAME = "db"
    }
}
