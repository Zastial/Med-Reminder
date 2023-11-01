package com.example.frontend_android.feature_prescription.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Prescription(
    @PrimaryKey val id: Int? = null,
    val titre: String,
    val contenu: String
        ) {



}