package com.example.frontend_android.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.frontend_android.data.model.entities.AlarmRecord
import com.example.frontend_android.data.model.entities.Doctor
import com.example.frontend_android.data.model.entities.MedicinePosology
import com.example.frontend_android.data.model.entities.Prescription

/**
 * Ceci est un object représentant l'ordonance avec ses relations:
 * - medicine_posologies
 * - doctor
 * - alarm
 */
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

    @Relation(
        parentColumn = "id",
        entityColumn = "prescription_id"
    )
    val alarmRecords: List<AlarmRecord>

    ) {
}