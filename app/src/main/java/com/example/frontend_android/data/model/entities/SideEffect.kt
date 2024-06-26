package com.example.frontend_android.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="SideEffect")
data class SideEffect(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val description: String,
    val medicine_id: Long,
    val prescription_id: Long,
    val date: String,
)

class InvalidSideEffectException(message: String): Exception(message)