package com.example.frontend_android.data.model

import androidx.room.ColumnInfo
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
    val doctor_id: Long,
) {
    val formatDate : String
        get() = delivered_at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

class InvalidPrescriptionException(message: String): Exception(message)