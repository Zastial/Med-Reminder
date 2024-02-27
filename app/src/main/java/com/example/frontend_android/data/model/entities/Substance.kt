package com.example.frontend_android.data.model.entities

data class Substance(
    val substanceName: String,
)

class InvalidSubstanceException(message: String): Exception(message)