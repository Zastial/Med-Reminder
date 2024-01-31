package com.example.frontend_android.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.frontend_android.data.model.entities.Alarm
import com.example.frontend_android.data.model.entities.Prescription

/**
 * Ceci est un object représentant une alarme avec sa prescription
 */
data class AlarmWithRelations(
    @Embedded
    val alarm: Alarm,

    @Relation(
        parentColumn = "prescription_id",
        entityColumn = "id"
    )
    val prescription: Prescription
)