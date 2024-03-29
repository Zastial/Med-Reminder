package com.example.frontend_android.utils

import android.util.Log
import com.example.frontend_android.ServiceBuilder
import com.example.frontend_android.data.model.dao.MedicineDao
import com.example.frontend_android.data.model.entities.Medicine
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone


val patterns = mutableListOf(
    "matin",
    "midi",
    "soir",
    "jour",
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
    "fois",
)

val patternsDosage = mutableListOf(
    Regex("(\\d+)\\s*mg\\.?\\b"),
    Regex("(\\d+)\\s*g\\.?\\b"),
    Regex("(\\d+)\\s*gr\\.?\\b"),
    Regex("(\\d+)\\s*gramme\\.?\\b"),
    Regex("(\\d+)\\s*gcp\\.?\\b"),
    Regex("(\\d+)\\s*ml\\.?\\b"),
    Regex("(.*)\\s*sucer"),
)

val patternsMonth = mutableListOf(
    "janvier",
    "février",
    "fevrier",
    "mars",
    "avril",
    "mai",
    "juin",
    "juillet",
    "août",
    "aout",
    "septembre",
    "octobre",
    "novembre",
    "décembre",
    "decembre",
)

val patternsMonthNumber = mutableListOf(
    "01",
    "02",
    "03",
    "04",
    "05",
    "06",
    "07",
    "08",
    "09",
    "10",
    "11",
    "12",
)

data class PrescriptionInfos(
    var nomDocteur: String,
    var emailDocteur: String,
    var medecineAndDosage: MutableList<Pair<Medicine, String>>,
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
            if (line.contains("Dr")) {
                doctorName = line.substring(3).trim()
            }
            if (line.contains("Docteur")) {
                doctorName = line.substring(8).trim()
            }
            if (line.contains("@")) {
                email = line
            }
        }
        return Pair(doctorName, email)
    }

    override fun extractMedicineAndDosage(lignesOrdonnance: List<String>): MutableList<Pair<Medicine, String>> {
        val medAndPos = mutableListOf<Pair<Medicine, String>>()

        for (line in lignesOrdonnance) {
            val medicine = Medicine("", "", 0, 0, 0, "", "", "", "", "", "", "")
            var posologie = ""

            for (pattern in patternsDosage) {
                val dosage = pattern.find(line.lowercase())

                // Ici on ne veut pas prendre en compte les lignes qui contiennent des mots de posologie
                // exemple : 6 comprimés à sucer par jour
                var lineContainsPosologyPattern = false
                for (patternPosology in patterns) {
                    lineContainsPosologyPattern = line.lowercase().contains(patternPosology)
                    if (lineContainsPosologyPattern) {
                        break
                    }
                }
                if (dosage != null && !lineContainsPosologyPattern) {
                    medicine.name = line.lowercase()
                }
            }

            for (patternPosology in patterns) {
                if (line.lowercase().contains(patternPosology)) {
                    posologie = line.lowercase()
                }
            }

            if (medicine.name.isNotEmpty()) {
                medAndPos += Pair(medicine, posologie)
            }
        }

        return medAndPos
    }

    override fun extractDate(textPrescription: List<String>): LocalDate {
        val zoneId = TimeZone.getDefault().toZoneId()

        for (line in textPrescription) {
            var potentialDate = line.split(Regex("\\s+")).find { it.contains("/") }
            if (potentialDate == null || potentialDate == "") {
                potentialDate = line.split(Regex("\\s+")).find { it.contains("-") }
            }

            potentialDate?.let {
                try {
                    val inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy")
                    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    val date = LocalDate.parse(potentialDate, inputFormatter)

                    return LocalDate.parse(date.format(outputFormatter), DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRANCE)).atStartOfDay(zoneId).toLocalDate()
                } catch (e: Exception) {
                    return LocalDate.now()
                }
            }
        }
        return LocalDate.now()
    }

}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun retrieveMedicine(name : String): Medicine? {
    return suspendCancellableCoroutine { continuation ->
        val medicineDao = ServiceBuilder.buildService(MedicineDao::class.java)
        var DBmedicine = Medicine("", "", 0, 0, 0, "", "", "", "", "", "", "")

        val requestCall = medicineDao.getMedicines(name)
        requestCall.enqueue(object : Callback<List<Medicine>> {
            override fun onResponse(
                call: Call<List<Medicine>>,
                response: Response<List<Medicine>>
            ) {
                if (response.isSuccessful) {
                    val medicineList = response.body()!!
                    Log.d("Med", medicineList.toString())

                    if (medicineList.isNotEmpty()) {
                        DBmedicine = medicineList[0]
                    }

                    if (DBmedicine.name.isNotEmpty()) {
                        continuation.resume(DBmedicine) {
                        }
                    } else {
                        continuation.resume(null) {
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Medicine>>, t: Throwable) {
                Log.d("Error", "onError: ${t.message}")
            }
        })
    }
}
