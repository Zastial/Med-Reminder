package com.example.frontend_android.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.frontend_android.data.model.Doctor
import com.example.frontend_android.data.model.MedicinePosology
import com.example.frontend_android.data.model.Prescription

data class PrescriptionWithRelations (
    @Embedded
    val prescription: Prescription,

    @Relation(
        parentColumn = "id",
        entityColumn = "prescription_id"
    )
    val medicine_posologies: List<MedicinePosology>,

    @Relation(
        parentColumn = "doctor_id",
        entityColumn = "id"
    )
    val doctor: Doctor,
) {
}