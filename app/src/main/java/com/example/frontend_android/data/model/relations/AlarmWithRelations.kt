package com.example.frontend_android.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.frontend_android.data.model.entities.AlarmRecord
import com.example.frontend_android.data.model.entities.Prescription

/**
 * Ceci est un object représentant une alarme avec sa prescription
 */
data class AlarmWithRelations(
    @Embedded
    val alarmRecord: AlarmRecord,

    @Relation(
        parentColumn = "prescription_id",
        entityColumn = "id"
    )
    val prescription: Prescription
)