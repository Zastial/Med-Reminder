package com.example.frontend_android.prescription.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.frontend_android.prescription.domain.model.Prescription
import com.example.frontend_android.prescription.domain.utils.Converters

@Database(
    entities = [Prescription::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PrescriptionDataBase : RoomDatabase() {

    abstract val prescriptionDao : PrescriptionDao

    companion object {
        const val DATABASE_NAME = "prescriptions_db"
    }
}