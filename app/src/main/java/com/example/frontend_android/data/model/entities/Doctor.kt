package com.example.frontend_android.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Doctor")
data class Doctor (
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val first_name: String,
    val last_name: String,
    val email: String?,
) {
}

class InvalidDoctorException(message: String): Exception(message)