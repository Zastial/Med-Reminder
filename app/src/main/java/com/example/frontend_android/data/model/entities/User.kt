package com.example.frontend_android.data.model.entities

data class User (
    val firstName: String,
    val lastName: String,
    val email: String,
    val allergies : MutableList<String>,
    val doctorId: Int?,
) {
    val fullName : String
        get() = "$firstName $lastName"
}

class InvalidUserException(message: String): Exception(message)