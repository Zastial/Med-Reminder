package com.example.frontend_android.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.frontend_android.ServiceBuilder
import com.example.frontend_android.alarm.manager.IScheduleAlarmManager
import com.example.frontend_android.alarm.manager.ScheduleAlarmManager
import com.example.frontend_android.data.AppDatabase
import com.example.frontend_android.data.model.dao.AlarmDao
import com.example.frontend_android.data.model.dao.MedicineDao
import com.example.frontend_android.data.model.dao.MedicinePosologyDao
import com.example.frontend_android.data.model.dao.PrescriptionDao
import com.example.frontend_android.data.model.dao.SideEffectDao
import com.example.frontend_android.utils.ITextExtractionFromImageService
import com.example.frontend_android.utils.TextExtractionFromImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideMedicinePosologyDao(database: AppDatabase): MedicinePosologyDao {
        return database.medicinePosologyDao()
    }

    @Provides
    @Singleton
    fun provideSideEffectDao(database: AppDatabase): SideEffectDao {
        return database.sideEffectDao()
    }

    @Provides
    @Singleton
    fun provideAlarmDao(database: AppDatabase): AlarmDao {
        return database.alarmDao()
    }

    @Provides
    @Singleton
    fun provideMedicineDao(): MedicineDao {
        return ServiceBuilder.buildService(MedicineDao::class.java)
    }

    @Provides
    @Singleton
    fun provideTextExtractionService(): ITextExtractionFromImageService {
        return TextExtractionFromImageService()
    }

    @Provides
    @Singleton
    fun provideScheduleAlarmManager(@ApplicationContext context : Context): IScheduleAlarmManager {
        return ScheduleAlarmManager(context)
    }

}