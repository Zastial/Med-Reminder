package com.example.frontend_android.data.model.entities

data class Medicine(
    var name: String,
    var form: String,
    var cis: Long,
    var cip7: Long,
    var cip13: Long,
    var price: String? = null,
    var dose: String,
    var administration: String,
    var generID: String? = null,
    var generName: String? = null,
    var generType: String? = null,
    var substanceName: String,
)

class InvalidMedicineException(message: String): Exception(message)