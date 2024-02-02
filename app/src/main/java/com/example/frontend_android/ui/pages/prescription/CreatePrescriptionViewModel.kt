package com.example.frontend_android.ui.pages.prescription

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.model.dao.PrescriptionDao
import com.example.frontend_android.data.model.entities.InvalidPrescriptionException
import com.example.frontend_android.data.model.entities.Prescription
import com.example.frontend_android.pages.prescription.creation_pages.FillPrescriptionInfos
import com.example.frontend_android.pages.prescription.creation_pages.ImportPrescriptionImage
import com.example.frontend_android.utils.ITextExtractionFromImageService
import com.example.frontend_android.utils.PrescriptionInfos
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.time.LocalDate
import javax.inject.Inject


data class CreatePrescriptionState (
    val step: Int = 0,
    val btnContinueEnabled : Boolean = true,
    val loading : Boolean = false,

    val imageUri: Uri? = null,
    val date : LocalDate = LocalDate.now(),
    val nom : String = "",
    val description : String = "",
    val nomDocteur : String = "",
    val emailDocteur : String = "",
    val medecineAndDosage : MutableList<Pair<String, String>> = mutableListOf()
)

@HiltViewModel
class CreatePrescriptionViewModel @Inject constructor(
    private val prescriptionDao: PrescriptionDao,
    private val textExtractionService : ITextExtractionFromImageService
): ViewModel() {

    private val _state = mutableStateOf(CreatePrescriptionState())
    val state: State<CreatePrescriptionState> = _state

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

    fun changeDocteurInformations(new_nom_docteur: String, new_email_docteur: String) {
        _state.value = state.value.copy(
            nomDocteur = new_nom_docteur,
            emailDocteur = new_email_docteur
        )
    }

    fun changeMedecineAndDosage(new_medecineAndDosage: MutableList<Pair<String, String>>) {
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

    fun stepToProgress() : Float {
        val res = state.value.step + 1
        return res / 6f // Changer 6 par nombre de pages dynamiquement
    }

    fun changeBtnContinueEnabled(new_value: Boolean) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                btnContinueEnabled = new_value
            )
        }
    }

    fun changeLoading(new_value: Boolean) {
        _state.value = state.value.copy(
            loading = new_value
        )
    }

    fun getImageFromUri(context : Context) {
        val image = InputImage.fromFilePath(context, state.value.imageUri!!)
        viewModelScope.launch {
            val result = textExtractionService.extractTextFromImage(image)
            withContext(viewModelScope.coroutineContext) {
                changeDate(result.date)
                changeDocteurInformations(result.nomDocteur, result.emailDocteur)
                changeMedecineAndDosage(result.medecineAndDosage)
            }
        }
    }

    @Composable
    fun PageFromStep() {
        return when (state.value.step) {
            0 -> ImportPrescriptionImage(this)
            1 -> FillPrescriptionInfos(this)
            else -> {}
        }
    }
}

