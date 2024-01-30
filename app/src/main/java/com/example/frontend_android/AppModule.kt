package com.example.frontend_android

import android.app.Application
import androidx.room.Room
import com.example.frontend_android.data.AppDatabase
import com.example.frontend_android.data.PrescriptionDao
import com.example.frontend_android.data.DoctorDao
import com.example.frontend_android.data.MedicinePosologyDao
import com.example.frontend_android.data.SideEffectDao
import com.example.frontend_android.pages.prescription.CreatePrescriptionModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePrescriptionDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePrescriptionDao(database: AppDatabase): PrescriptionDao {
        return database.prescriptionDao()
    }

    @Provides
    @Singleton
    fun provideDoctorDao(database: AppDatabase): DoctorDao {
        return database.doctorDao()
    }

    @Provides
    @Singleton
    fun provideMedicinePosologyDao(database: AppDatabase): MedicinePosologyDao {
        return database.medicinePosologyDao()
    }

    @Provides
    @Singleton
    fun provideSideEffectDao(database: AppDatabase): SideEffectDao {
        return database.sideEffectDao()
    }

}