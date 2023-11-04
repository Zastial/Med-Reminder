package com.example.frontend_android.feature_prescription.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.frontend_android.feature_prescription.domain.model.Prescription

@Database(
    entities = [Prescription::class],
    version = 1
)
abstract class PrescriptionDataBase : RoomDatabase() {

    abstract val prescriptionDao : PrescriptionDao
}