package com.example.frontend_android.utils

import com.example.frontend_android.data.model.entities.Medicine
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import dagger.Provides
import java.time.LocalDate


interface ITextExtractionFromImageService {
    suspend fun extractTextFromImage(image : InputImage?) : PrescriptionInfos

    fun extractNomAndEmailMedecin(textPrescription: List<String>): Pair<String, String>

    fun extractMedicineAndDosage(lignesOrdonnance: List<String>): MutableList<Pair<Medicine, String>>

    fun extractDate(textPrescription: List<String>): LocalDate
}