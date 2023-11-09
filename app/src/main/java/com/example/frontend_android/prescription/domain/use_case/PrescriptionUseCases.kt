package com.example.frontend_android.prescription.domain.use_case

data class PrescriptionUseCases(
    val addPrescription: AddPrescription,
    val deletePrescription: DeletePrescription,
    val getPrescription: GetPrescription,
    val getPrescriptions: GetPrescriptions
)
