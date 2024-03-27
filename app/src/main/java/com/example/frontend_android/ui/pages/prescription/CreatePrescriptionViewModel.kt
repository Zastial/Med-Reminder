package com.example.frontend_android.ui.pages.prescription

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.frontend_android.data.model.dao.PrescriptionDao
import com.example.frontend_android.data.model.entities.InvalidPrescriptionException
import com.example.frontend_android.data.model.entities.Medicine
import com.example.frontend_android.data.model.entities.Prescription
import com.example.frontend_android.ui.pages.prescription.creation_pages.ImportPrescriptionImage
import com.example.frontend_android.ui.pages.prescription.creation_pages.AdditionalInfos
import com.example.frontend_android.ui.pages.prescription.creation_pages.Loading
import com.example.frontend_android.ui.pages.prescription.creation_pages.MedicinesAssociated
import com.example.frontend_android.ui.pages.prescription.creation_pages.PrescriptionInfos
import com.example.frontend_android.utils.ITextExtractionFromImageService
import com.example.frontend_android.utils.retrieveMedicine
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


data class CreatePrescriptionState (
    val step: Int = 0,
    val btnContinueEnabled : Boolean = true,
    val loading : Boolean = false,
    var isBottomSheetOpen : Boolean = false,

    val imageUri: Uri? = null,
    val date : LocalDate = LocalDate.now(),
    val nom : String = "Ordonnance du " +
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm")),
    val description : String = "",
    val nomDocteur : String = "",
    val emailDocteur : String = "",
    val medecineAndDosage : MutableList<Pair<Medicine, String>> = mutableListOf(),
)

@HiltViewModel
class CreatePrescriptionViewModel @Inject constructor(
    private val prescriptionDao: PrescriptionDao,
    private val textExtractionService : ITextExtractionFromImageService,
    @ApplicationContext context : Context
): ViewModel() {

    private val _state = mutableStateOf(CreatePrescriptionState())
    val state: State<CreatePrescriptionState> = _state
    val sharedPreferences = context.getSharedPreferences("user_infos", Context.MODE_PRIVATE)

    @Throws(InvalidPrescriptionException::class)
    fun insertPrescription() {
        viewModelScope.launch {
            try {
                prescriptionDao.insertPrescription(
                    Prescription(
                        id = null,
                        title = "",
                        description = "",
                        delivered_at = LocalDate.now(),
                        doctor_first_name = null,
                        doctor_last_name = null,
                        doctor_email = null,

                    )
                )
            } catch (e: InvalidPrescriptionException) {
                Log.d("ErrorInvalidPrescription", e.message!!)
            }
        }
    }

    fun resetState() {
        _state.value = CreatePrescriptionState()
    }

    fun changeImageUri(new_uri: Uri) {
        _state.value = state.value.copy(
            imageUri = new_uri
        )
    }

    fun changeDate(new_date: LocalDate) {
        _state.value = state.value.copy(
            date = new_date
        )
    }

    fun changeNom(new_nom: String) {
        _state.value = state.value.copy(
            nom = new_nom
        )
    }

    fun changeDescription(new_description: String) {
        _state.value = state.value.copy(
            description = new_description
        )
    }

    fun changenomDocteur(new_nom_docteur: String) {
        _state.value = state.value.copy(
            nomDocteur = new_nom_docteur
        )
    }

    fun changeEmailDocteur(new_email_docteur: String) {
        _state.value = state.value.copy(
            emailDocteur = new_email_docteur
        )
    }

    fun changeMedecineAndDosage(new_medecineAndDosage: MutableList<Pair<Medicine, String>>) {
        _state.value = state.value.copy(
            medecineAndDosage = new_medecineAndDosage
        )
    }

    fun nextPage() {
        if (state.value.step == 6) return
        _state.value = state.value.copy(
            step = state.value.step + 1
        )
    }

    fun previousPage() {
        if (state.value.step == 0) return
        _state.value = state.value.copy(
            step = state.value.step - 1
        )
    }

    fun changeStep(newStep: Int) {
        _state.value = state.value.copy(
            step = newStep
        )
    }

    fun changeBottomSheetBool(new_value: Boolean){
        _state.value = state.value.copy(
            isBottomSheetOpen = new_value
        )
    }

    fun stepToProgress() : Float {
        // We ignore the loading page as a step

        val res = state.value.step + 1
        if (state.value.step == 0) {
            return res / 7f
        }
        return (res-1)/ 7f // Changer 7 par nombre de pages dynamiquement
    }

    fun getInformationsFromUri(context : Context) {
        viewModelScope.launch {
            val image = InputImage.fromFilePath(context, state.value.imageUri!!)

            val result = textExtractionService.extractTextFromImage(image)

            val medAndPos = mutableListOf<Pair<Medicine, String>>()
            for (med in result.medecineAndDosage) {
                val medicine = retrieveMedicine(med.first.name)
                if (medicine != null) {
                    medAndPos.add(Pair(medicine, med.second))
                }
            }

            withContext(viewModelScope.coroutineContext) {
                changeDate(result.date)
                changenomDocteur(result.nomDocteur)
                changeEmailDocteur(result.emailDocteur)
                changeMedecineAndDosage(medAndPos)

                if (state.value.nomDocteur.isNotEmpty()) {
                    val prescriptionName = state.value.nomDocteur + "_" + state.value.date
                    changeNom(prescriptionName.replace(" ", "_"))
                }
            }
        }.invokeOnCompletion {
            nextPage()
        }
    }

    @Composable
    fun PageFromStep(navcontroller : NavController) {
        return when (state.value.step) {
            0 -> ImportPrescriptionImage(navcontroller, this)
            1 -> Loading(this) // Not a concrete page, to integrate into the process
            2 -> PrescriptionInfos(this)
            3 -> AdditionalInfos(this)
            4 -> MedicinesAssociated(navcontroller, this)
            7 -> ViewCameraScreen(navcontroller, this)
            else -> {}
        }
    }
}
