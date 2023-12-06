package com.example.frontend_android.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.frontend_android.data.model.Prescription
import com.example.frontend_android.data.model.SideEffect

/**
 * Ceci est un object représentant un effet secondaire avec ses relations:
 * - prescription
 */
data class SideEffectWithRelations(
    @Embedded
    val side_effect: SideEffect,
    @Relation(
        parentColumn = "prescription_id",
        entityColumn = "id"
    )
    val prescription: Prescription
)