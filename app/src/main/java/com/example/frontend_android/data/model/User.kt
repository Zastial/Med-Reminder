package com.example.frontend_android.data.model

data class User (
    val firstName: String,
    val lastName: String,
    val email: String,
    val allergies : MutableList<String>,
    val doctor_first_name: String?,
    val doctor_last_name: String?,
    val doctor_email: String?
) {
    val fullName : String
        get() = "$firstName $lastName"
}

class InvalidUserException(message: String): Exception(message)