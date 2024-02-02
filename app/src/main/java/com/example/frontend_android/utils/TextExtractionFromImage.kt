package com.example.frontend_android.utils

import android.content.Context
import android.util.Log
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


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

class TextExtractionFromImage {
    var image: InputImage? = null

    fun onTranslateButtonClick(context : Context, viewModel : CreatePrescriptionViewModel) {
        try {
            image = InputImage.fromFilePath(context, viewModel.state.value.imageUri!!)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        image?.let { it ->
            TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).process(it)
                .addOnSuccessListener { text ->
                    val listOrdonnance = text.text.split("\n").map { it.trim() }

                    val nameAndEmail = extractNomAndEmailMedecin(listOrdonnance)
                    viewModel.changeDocteurInformations(nameAndEmail.first, nameAndEmail.second)

                    val medAndPos = extractMedicineAndDosage(listOrdonnance)
                    viewModel.changeMedecineAndDosage(medAndPos)

                    val date = extractDate(listOrdonnance)
                    viewModel.changeDate(date)
                }
        }
    }

    private fun extractNomAndEmailMedecin(textPrescription: List<String>): Pair<String, String> {
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

    private fun extractMedicineAndDosage(lignesOrdonnance: List<String>): MutableList<Pair<String, String>> {
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

    private fun extractDate(textPrescription: List<String>): LocalDate {
        val zoneId = ZoneId.of("Europe/Paris")

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

