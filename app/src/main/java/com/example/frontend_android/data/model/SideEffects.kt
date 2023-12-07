package com.example.frontend_android.data.model

data class SideEffects (
    val description: String,
    val medecineID: Int?,
    val prescriptionID: Int?,
)

class InvalidSideEffectsException(message: String): Exception(message)