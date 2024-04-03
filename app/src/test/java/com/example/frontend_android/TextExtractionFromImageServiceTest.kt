package com.example.frontend_android

import com.example.frontend_android.data.model.entities.Medicine
import com.example.frontend_android.utils.TextExtractionFromImageService
import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate

class TextExtractionFromImageServiceTest {

    @Test
    fun extractNomAndEmailMedecin_returnsCorrectDoctorNameAndEmail() {
        val text = listOf(
            "Dr. John Doe",
            "john.doe@example.com",
            "Prescription details..."
        )

        val expected = Pair("John Doe", "john.doe@example.com")

        val result = TextExtractionFromImageService().extractNomAndEmailMedecin(text)
        assertEquals(expected, result)
    }

    @Test
    fun extractNomAndEmailMedecin_returnsCorrectDoctorNameAndEmailWhenDoctorTitleIsDocteur() {
        val text = listOf(
            "Docteur Jane Doe",
            "jane.doe@example.com",
            "Prescription details..."
        )

        val expected = Pair("Jane Doe", "jane.doe@example.com")

        val result = TextExtractionFromImageService().extractNomAndEmailMedecin(text)
        assertEquals(expected, result)
    }

    @Test
    fun extractNomAndEmailMedecin_returnsEmptyStringsWhenNoDoctorNameAndEmail() {
        val text = listOf(
            "Prescription details...",
            "No doctor name and email"
        )

        val expected = Pair("", "")

        val result = TextExtractionFromImageService().extractNomAndEmailMedecin(text)
        assertEquals(expected, result)
    }

    @Test
    fun extractNomAndEmailMedecin_returnsCorrectDoctorNameAndEmailWhenEmailIsOnSameLine() {
        val text = listOf(
            "Dr. John Doe john.doe@example.com",
            "Prescription details..."
        )

        val expected = Pair("John Doe", "john.doe@example.com")

        val result = TextExtractionFromImageService().extractNomAndEmailMedecin(text)
        assertEquals(expected, result)
    }

    @Test
    fun extractMedicineAndDosage_returnsCorrectMedicine() {
        val text = listOf(
            "Paracetamol 500mg",
            "Amoxicillin 250mg",
            "Ibuprofen 200mg"
        )

        val expected = mutableListOf(
            Pair(Medicine("paracetamol 500mg", "", 0, 0, 0, "", "", "", "", "", "", ""), ""),
            Pair(Medicine("amoxicillin 250mg", "", 0, 0, 0, "", "", "", "", "", "", ""), ""),
            Pair(Medicine("ibuprofen 200mg", "", 0, 0, 0, "", "", "", "", "", "", ""), "")
        )

        val result = TextExtractionFromImageService().extractMedicineAndDosage(text)
        assertEquals(expected, result)
    }

    @Test
    fun extractMedicineAndDosage_returnsEmptyListWhenNoMedicineAndDosage() {
        val text = listOf(
            "No medication prescribed",
            "Patient advised to rest and hydrate"
        )

        val expected = mutableListOf<Pair<Medicine, String>>()

        val result = TextExtractionFromImageService().extractMedicineAndDosage(text)
        assertEquals(expected, result)
    }

    @Test
    fun extractMedicineAndDosage_returnsCorrectMedicineAndDosage() {
        val text = listOf(
            "Paracetamol 500mg",
            "1 dose au réveil",
            "Amoxicillin 250mg",
            "Ibuprofen 200mg"
        )

        val expected = mutableListOf(
            Pair(Medicine("paracetamol 500mg", "", 0, 0, 0, "", "", "", "", "", "", ""), "1 dose au réveil"),
            Pair(Medicine("amoxicillin 250mg", "", 0, 0, 0, "", "", "", "", "", "", ""), ""),
            Pair(Medicine("ibuprofen 200mg", "", 0, 0, 0, "", "", "", "", "", "", ""), "")
        )

        val result = TextExtractionFromImageService().extractMedicineAndDosage(text)
        assertEquals(expected, result)
    }

    @Test
    fun extractDate_working() {
        val text = listOf(
            "Bonjour, comment ça va ?",
            "Aujourd'hui, il fait beau.",
            "La date d'aujourd'hui est le 15/12/2023",
            "J'aime programmer en Kotlin.",
            "Demain, je vais au cinéma.",
            "Hier, c'était l'anniversaire de mon ami."
        )

        val expected = "2023-12-15"
        val result = TextExtractionFromImageService().extractDate(text)
        assertEquals(expected, result.toString())
    }

    @Test
    fun extractDate_with_dot() {
        val text = listOf(
            "Bonjour, comment ça va ?",
            "Aujourd'hui, il fait beau.",
            "La date d'aujourd'hui est le 15/12/2023.",
            "J'aime programmer en Kotlin.",
            "Demain, je vais au cinéma.",
            "Hier, c'était l'anniversaire de mon ami."
        )

        val expected = "2023-12-15"
        val result = TextExtractionFromImageService().extractDate(text)
        assertEquals(expected, result.toString())
    }

    @Test
    fun extractDate_no_date() {
        val text = listOf(
            "Bonjour, comment ça va ?",
            "Aujourd'hui, il fait beau.",
            "J'aime programmer en Kotlin.",
            "Demain, je vais au cinéma.",
            "Hier, c'était l'anniversaire de mon ami."
        )

        val expected = LocalDate.now().toString()
        val result = TextExtractionFromImageService().extractDate(text)
        assertEquals(expected, result.toString())
    }
}