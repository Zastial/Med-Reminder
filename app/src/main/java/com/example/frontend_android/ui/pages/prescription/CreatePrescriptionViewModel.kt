package com.example.frontend_android.ui.pages.prescription

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.frontend_android.ServiceBuilder
import com.example.frontend_android.data.model.dao.MedicineDao
import com.example.frontend_android.data.model.dao.MedicinePosologyDao
import com.example.frontend_android.data.model.dao.PrescriptionDao
import com.example.frontend_android.data.model.entities.InvalidPrescriptionException
import com.example.frontend_android.data.model.entities.Medicine
import com.example.frontend_android.data.model.entities.MedicinePosology
import com.example.frontend_android.data.model.entities.Prescription
import com.example.frontend_android.data.model.entities.emptyMedicine
import com.example.frontend_android.ui.pages.prescription.creation_pages.ImportPrescriptionImage
import com.example.frontend_android.ui.pages.prescription.creation_pages.AdditionalInfos
import com.example.frontend_android.ui.components.Loading
import com.example.frontend_android.ui.pages.prescription.creation_pages.AddMedicinesAssociated
import com.example.frontend_android.ui.pages.prescription.creation_pages.MedicinesAssociated
import com.example.frontend_android.ui.pages.prescription.creation_pages.PrescriptionInfos
import com.example.frontend_android.ui.pages.prescription.creation_pages.RecapPrescription
import com.example.frontend_android.ui.pages.prescription.creation_pages.SearchMedicinesAssociated
import com.example.frontend_android.utils.ITextExtractionFromImageService
import com.example.frontend_android.utils.retrieveMedicine
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


data class CreatePrescriptionState (
    val step: Int = 0,
    val btnContinueEnabled : Boolean = true,
    val loading : Boolean = false,
    val medicineAddedId : Long = 0,
    val medicineClicked : Medicine = emptyMedicine,
    val posology : String = "",
    val old_posology : String = "",

    val imageUri: Uri? = null,
    val date : LocalDate = LocalDate.now(),
    val nom : String = "Ordonnance du " +
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm")),
    val description : String = "",
    val nomDocteur : String = "",
    val emailDocteur : String = "",
    val medecineAndDosage : SnapshotStateList<Pair<Medicine, String>> = mutableStateListOf(),
    var isBottomSheetOpen : Boolean = false
)

@HiltViewModel
class CreatePrescriptionViewModel @Inject constructor(
    private val prescriptionDao: PrescriptionDao,
    private val medicinePosologyDao: MedicinePosologyDao,
    private val textExtractionService : ITextExtractionFromImageService
): ViewModel() {

    private val _state = mutableStateOf(CreatePrescriptionState())
    val state: State<CreatePrescriptionState> = _state

    private val medicineDao  = ServiceBuilder.buildService(MedicineDao::class.java)

    @Throws(InvalidPrescriptionException::class)
    fun insertPrescription() {
        viewModelScope.launch {
            try {
                val prescriptionID = prescriptionDao.insertPrescription(
                    Prescription(
                        id = null,
                        title = state.value.nom,
                        description = state.value.description,
                        delivered_at = state.value.date,
                        doctor_name = state.value.nomDocteur,
                        doctor_email = state.value.emailDocteur,
                    )
                )

                for (med in state.value.medecineAndDosage) {
                    medicinePosologyDao.insertMedicinePosology(
                        MedicinePosology(
                            id = null,
                            description = med.second,
                            medicine_id = med.first.cis,
                            prescription_id = prescriptionID
                        )
                    )
                }

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

    fun changeMedecineAndDosage(new_medecineAndDosage: SnapshotStateList<Pair<Medicine, String>>) {
        _state.value = state.value.copy(
            medecineAndDosage = new_medecineAndDosage
        )
    }

    fun nextPage() {
        _state.value = state.value.copy(
            step = state.value.step + 1
        )
    }

    fun previousPage() {
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

    fun changeMedicineAddedId(new_value: Long){
        _state.value = state.value.copy(
            medicineAddedId = new_value
        )
    }

    fun changePosologyMedicine(posology: String){
        _state.value = state.value.copy(
            posology = posology
        )
    }

    fun changeOldPosology(posology: String){
        _state.value = state.value.copy(
            old_posology = posology
        )
    }

    fun deleteMedicineAssociated(medicine_posology: Pair<Medicine, String>){
        state.value.medecineAndDosage.remove(medicine_posology)
        _state.value = state.value.copy()
    }

    fun addMedicineAssociated(medicine_posology: Pair<Medicine, String>){
        state.value.medecineAndDosage.add(medicine_posology)
        _state.value = state.value.copy(posology = "", medicineClicked = emptyMedicine)

    }

    fun changeMedicineClicked(medicine: Medicine){
        _state.value = state.value.copy(
            medicineClicked = medicine
        )
        Log.d("clicked", state.value.medicineClicked.toString())
    }

    fun stepToProgress() : Float {
        // We ignore the loading page as a step

        val res = state.value.step + 1
        if (state.value.step == 0) {
            return res / 6f
        }
        return (res-1)/ 6f // Changer 7 par nombre de pages dynamiquement
    }

    fun getInformationsFromUri(context : Context) {
        viewModelScope.launch {
            val image = InputImage.fromFilePath(context, state.value.imageUri!!)

            val result = textExtractionService.extractTextFromImage(image)

            val medAndPos = mutableStateListOf<Pair<Medicine, String>>()
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

    suspend fun retrieveOneMedicine(cis: Long):Medicine? {
        return suspendCancellableCoroutine { continuation ->
            val requestCall = medicineDao.getMedicine(cis)
            var DBmedicine = Medicine("", "", 0, 0, 0, "", "", "", "", "", "", "")

            requestCall.enqueue(object : Callback<Medicine> {
                override fun onResponse(
                    call: Call<Medicine>,
                    response: Response<Medicine>
                ) {
                    if (response.isSuccessful) {
                        val medicine = response.body()!!
                        DBmedicine = medicine

                        if (DBmedicine.name.isNotEmpty()) {
                            continuation.resume(DBmedicine) {
                            }
                        } else {
                            continuation.resume(null) {
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<Medicine>, t: Throwable) {
                    Log.d("Error", "onError: ${t.message}")
                }
            })
        }
    }

    @Composable
    fun PageFromStep(navcontroller : NavController) {
        val context = LocalContext.current

        // if URI is present then wait till image is loaded
        val URILaunchedEffect: @Composable () -> Unit = {
            LaunchedEffect(
                key1 = state.value.imageUri,
            ) {
                if (!Uri.EMPTY.equals(state.value.imageUri) && state.value.imageUri != null) {
                    getInformationsFromUri(context)
                }
            }
        }

        val medicineLaunchedEffect: @Composable () -> Unit = {
            LaunchedEffect(
                key1 = state.value.medicineAddedId,
            ) {
                if (state.value.medicineAddedId != 0L) {
                    viewModelScope.launch {
                        var DBmedicine = retrieveOneMedicine(state.value.medicineAddedId)
                        withContext(viewModelScope.coroutineContext) {
                            changeMedicineClicked(DBmedicine!!)
                        }
                    }.invokeOnCompletion {
                        changeStep(9)
                    }
                }
            }
        }

        return when (state.value.step) {
            0 -> ImportPrescriptionImage(navcontroller, this)
            1 -> Loading(URILaunchedEffect) // Not a concrete page, to integrate into the process
            2 -> PrescriptionInfos(this)
            3 -> AdditionalInfos(this)
            4 -> MedicinesAssociated(navcontroller, this)
            5 -> RecapPrescription(navcontroller, this)
            6 -> ViewCameraScreen(navcontroller, this)
            7 -> SearchMedicinesAssociated(navController = navcontroller, prescriptionViewModel = this)
            8 -> Loading(medicineLaunchedEffect)
            9 -> AddMedicinesAssociated(navController = navcontroller, viewModel = this)
            else -> {}
        }
    }
}
