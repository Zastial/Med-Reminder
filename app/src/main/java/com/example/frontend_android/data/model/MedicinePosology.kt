package com.example.frontend_android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="MedicinePosology")
data class MedicinePosology (
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val description: String,
    val medicine_id: Long,
    val prescription_id: Long,
)

class InvalidMedicinePosologyException(message: String): Exception(message)