package com.example.frontend_android.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.frontend_android.data.model.MedicinePosology
import com.example.frontend_android.data.model.Prescription

/**
 * Ceci est un object représentant l'ordonance avec ses relations:
 * - medicine_posologies
 */
data class PrescriptionWithRelations (
    @Embedded
    val prescription: Prescription,

    @Relation(
        parentColumn = "id",
        entityColumn = "prescription_id"
    )
    val medicine_posologies: List<MedicinePosology>,
) {
}