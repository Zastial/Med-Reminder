package com.example.frontend_android.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName="Prescription")
data class Prescription (
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val title: String,
    val description: String,
    val delivered_at: LocalDate,
    val doctor_first_name: String?,
    val doctor_last_name: String?,
    val doctor_email: String?
) {
    val formatDate : String
        get() = delivered_at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

class InvalidPrescriptionException(message: String): Exception(message)