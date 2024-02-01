package com.example.frontend_android.data.model.entities

data class Medicine(
    val name: String,
    val form: String,
    val cis: Long,
    val cip7: Long,
    val cip13: Long,
    val price: String,
    val administration: String,
    val generID: String? = null,
    val generName: String? = null,
    val generType: String? = null,
)

class InvalidMedicineException(message: String): Exception(message)