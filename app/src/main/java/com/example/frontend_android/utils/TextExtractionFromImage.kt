package com.example.frontend_android.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.TimeZone


val patterns = mutableListOf(
    "matin",
    "midi",
    "soir",
    "jours",
    "coucher",
    "réveil",
    "reveil",
    "avant",
    "après",
    "apres",
    "pendant",
    "heure",
    "heures",
)

val patternsDosage = mutableListOf(
    Regex("(\\d+)\\s*mg"),
    Regex("(\\d+)\\s*g"),
    Regex("(\\d+)\\s*ml"),
)

data class PrescriptionInfos(
    var nomDocteur: String,
    var emailDocteur: String,
    var medecineAndDosage: MutableList<Pair<String, String>>,
    var date: LocalDate
)


class TextExtractionFromImageService : ITextExtractionFromImageService {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun extractTextFromImage(image: InputImage?): PrescriptionInfos {
        return suspendCancellableCoroutine { continuation ->
            val prescriptionInfos = PrescriptionInfos("", "", mutableListOf(), LocalDate.now())
            val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

            val process = image?.let {
                textRecognizer.process(it)
                    .addOnSuccessListener { textResult ->
                        val text = textResult.text
                        val listOrdonnance = text.split("\n").map { it.trim() }

                        val (nomDocteur, emailDocteur) = extractNomAndEmailMedecin(listOrdonnance)
                        prescriptionInfos.nomDocteur = nomDocteur
                        prescriptionInfos.emailDocteur = emailDocteur

                        val medecineAndDosage = extractMedicineAndDosage(listOrdonnance)
                        prescriptionInfos.medecineAndDosage = medecineAndDosage

                        val date = extractDate(listOrdonnance)
                        prescriptionInfos.date = date

                        continuation.resume(prescriptionInfos) {
                            // Optional: handle cancellation if needed
                        }
                    }
                    .addOnFailureListener { e ->
                        continuation.resume(prescriptionInfos) {
                            // Optional: handle cancellation if needed
                        }
                    }
            }
        }
    }

    override fun extractNomAndEmailMedecin(textPrescription: List<String>): Pair<String, String> {
        var doctorName = ""
        var email = ""
        for (line in textPrescription) {
            if (line.contains("Dr") || line.contains("Docteur")) {
                doctorName = line.substring(3).trim()
            }
            if (line.contains("@")) {
                email = line
            }
        }
        return Pair(doctorName, email)
    }

    override fun extractMedicineAndDosage(lignesOrdonnance: List<String>): MutableList<Pair<String, String>> {
        var posologie = ""
        var medicine = ""
        val medAndPos = mutableListOf<Pair<String, String>>()

        for (line in lignesOrdonnance) {
            for (pattern in patternsDosage) {
                val dosage = pattern.find(line.lowercase())
                if (dosage != null) {
                    medicine = line
                }
            }
            for (pattern in patterns) {
                if (line.lowercase().contains(pattern)) {
                    posologie = line
                }
            }

            if (medicine.isNotEmpty() && posologie.isNotEmpty()) {
                medAndPos += Pair(medicine, posologie)
                medicine = ""
                posologie = ""
            }
        }
        return medAndPos
    }

    override fun extractDate(textPrescription: List<String>): LocalDate {
        val zoneId = TimeZone.getDefault().toZoneId()

        for (line in textPrescription) {
            val potentialDate = line.split(Regex("\\s+")).find { it.contains("/") }
            potentialDate?.let {
                try {
                    return LocalDate.parse(it, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(zoneId).toLocalDate()
                } catch (e: Exception) {
                    return LocalDate.now()
                }
            }
        }
        return LocalDate.now()
    }

}

