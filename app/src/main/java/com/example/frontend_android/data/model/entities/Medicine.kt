package com.example.frontend_android.data.model.entities

data class Medicine(
    val name: String,
    val form: String,
    val cis: Long,
    val cip7: Long,
    val cip13: Long,
    val price: String? = null,
    val dose: String,
    val administration: String,
    val generID: String? = null,
    val generName: String? = null,
    val generType: String? = null,
    val substanceName: String,
)

class InvalidMedicineException(message: String): Exception(message)