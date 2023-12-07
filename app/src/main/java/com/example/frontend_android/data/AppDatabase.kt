package com.example.frontend_android.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.frontend_android.data.model.Doctor
import com.example.frontend_android.data.model.MedicinePosology
import com.example.frontend_android.data.model.Prescription
import com.example.frontend_android.data.model.SideEffect
import com.example.frontend_android.utils.Converters

@Database(entities = [
        Prescription::class,
        Doctor::class,
        MedicinePosology::class,
        SideEffect::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun prescriptionDao(): PrescriptionDao
    abstract fun doctorDao(): DoctorDao
    abstract fun medicinePosologyDao(): MedicinePosologyDao
    abstract fun sideEffectDao(): SideEffectDao


    companion object {
        const val DATABASE_NAME = "db"
    }
}
