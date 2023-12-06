package com.example.frontend_android.data.model

data class Medicine(
    val id: Int?,
    val name: String,
)

class InvalidMedicineException(message: String): Exception(message)