package com.example.frontend_android.di

import com.example.frontend_android.prescription.data.data_source.PrescriptionDataBase
import android.app.Application
import androidx.room.Room
import com.example.frontend_android.prescription.data.repository.PrescriptionRepositoryImpl
import com.example.frontend_android.prescription.domain.repository.PrescriptionRepository
import com.example.frontend_android.prescription.domain.use_case.AddPrescription
import com.example.frontend_android.prescription.domain.use_case.DeletePrescription
import com.example.frontend_android.prescription.domain.use_case.GetPrescription
import com.example.frontend_android.prescription.domain.use_case.GetPrescriptions
import com.example.frontend_android.prescription.domain.use_case.PrescriptionUseCases
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
    fun providePrescriptionDatabase(app: Application): PrescriptionDataBase {
        return Room.databaseBuilder(
            app,
            PrescriptionDataBase::class.java,
            PrescriptionDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePrescriptionRepository(db: PrescriptionDataBase): PrescriptionRepository {
        return PrescriptionRepositoryImpl(db.prescriptionDao)
    }

    @Provides
    @Singleton
    fun providePrescriptionUseCases(repository: PrescriptionRepository): PrescriptionUseCases {
        return PrescriptionUseCases(
            getPrescription = GetPrescription(repository),
            deletePrescription = DeletePrescription(repository),
            addPrescription = AddPrescription(repository),
            getPrescriptions = GetPrescriptions(repository)
        )
    }
}