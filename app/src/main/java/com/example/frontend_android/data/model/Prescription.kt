package com.example.frontend_android.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity
data class Prescription (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val description: String,
    val deliveredAt: LocalDate,
) {
    val formatDate : String
        get() = deliveredAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

}

class InvalidPrescriptionException(message: String): Exception(message)