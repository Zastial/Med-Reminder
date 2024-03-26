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

var defaultMedicine = Medicine(
    name = "ABILIFY 15 mg, comprimé",
    form = "comprimé",
    cis = 64224801,
    cip7 = 4950021,
    cip13 = 3400949500215,
    price = "22,65",
    dose = "15 mg",
    administration = "orale",
    generID = "1248",
    generName = "ARIPIPRAZOLE 15 mg - ABILIFY 15 mg, comprimé",
    generType = "0",
    substanceName = "PARACÉTAMOL",
)