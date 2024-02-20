package com.example.frontend_android.data.model.entities

data class User (
    val first_name: String,
    val last_name: String,
    val email: String,
    val allergies : MutableList<String>,
    val doctor_first_name: String?,
    val doctor_last_name: String?,
    val doctor_email: String?
) {
    val fullName : String
        get() = "$first_name $last_name"
}

class InvalidUserException(message: String): Exception(message)