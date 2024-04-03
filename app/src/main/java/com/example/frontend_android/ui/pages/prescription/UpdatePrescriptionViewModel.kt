package com.example.frontend_android.ui.pages.prescription

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.frontend_android.data.model.dao.MedicineDao
import com.example.frontend_android.data.model.dao.MedicinePosologyDao
import com.example.frontend_android.data.model.dao.PrescriptionDao
import com.example.frontend_android.data.model.entities.InvalidPrescriptionException
import com.example.frontend_android.data.model.entities.Medicine
import com.example.frontend_android.data.model.entities.MedicinePosology
import com.example.frontend_android.data.model.entities.Prescription
import com.example.frontend_android.utils.ITextExtractionFromImageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class UpdatePrescriptionState (
    val isLoading : Boolean = true,

    val date : LocalDate = LocalDate.now(),
    val title : String = "",
    val description : String = "",
    val doctor_name : String = "",
    val doctor_email : String = "",
    val medicines_posology : MutableList<Pair<Medicine, String>> = mutableListOf(),

    var isBottomSheetOpen : Boolean = false
)

@HiltViewModel
class UpdatePrescriptionViewModel @Inject constructor(
    private val prescriptionDao: PrescriptionDao,
    private val medicineDao: MedicineDao,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _state = mutableStateOf(UpdatePrescriptionState())
    val state: State<UpdatePrescriptionState> = _state


    private val prescriptionID: Long = checkNotNull(savedStateHandle["prescription_id"])


    init {
        retrievePrescription()
    }

    fun updatePrescription() {
        viewModelScope.launch {
            try {
                prescriptionDao.insertPrescription(
                    Prescription(
                        id = prescriptionID,
                        title = _state.value.title,
                        description = _state.value.description,
                        delivered_at = _state.value.date,
                        doctor_name = _state.value.doctor_name,
                        doctor_email = _state.value.doctor_email,
                    )
                )

                /* Update this when the medicine posologies can be udpated
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
                */

            } catch (e: InvalidPrescriptionException) {
                Log.d("ErrorInvalidPrescription", e.message!!)
            }
        }
    }


    private fun retrievePrescription() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = prescriptionDao.getPrescription(prescriptionID)
                ?: throw Error("The prescription could not have been found")

            val prescription = result.prescription
            val medicinePosologies = result.medicine_posologies

            Log.d("pres", medicinePosologies.toString())
            Log.d("pres", prescription.toString())

            val medicines: List<Pair<Medicine, String>> = medicinePosologies.map {
                val response = medicineDao.getMedicine(it.medicine_id)
                val posology = it.description
                Pair(response.execute().body()!!, posology)
            }

            Log.d("pres", medicines.toString())

            withContext(Dispatchers.Main) {
                changeDate(prescription.delivered_at)
                changeTitle(prescription.title)
                changeDescription(prescription.description)
                changeDoctorName(prescription.doctor_name ?: "")
                changeDoctorEmail(prescription.doctor_email ?: "")
                changeMedicinesPosology(medicines.toMutableList())
            }
        }.invokeOnCompletion {
            _state.value = state.value.copy(
                isLoading = false
            )
        }
    }

    fun changeDate(new_date: LocalDate) {
        _state.value = state.value.copy(
            date = new_date
        )
    }

    fun changeTitle(new_title: String) {
        _state.value = state.value.copy(
            title = new_title
        )
    }

    fun changeDescription(new_description: String) {
        _state.value = state.value.copy(
            description = new_description
        )
    }

    fun changeDoctorName(new_doctor_name: String) {
        _state.value = state.value.copy(
            doctor_name = new_doctor_name
        )
    }

    fun changeDoctorEmail(new_doctor_email: String) {
        _state.value = state.value.copy(
            doctor_email = new_doctor_email
        )
    }

    fun changeMedicinesPosology(new_medicines_posologies: MutableList<Pair<Medicine, String>>) {
        _state.value = state.value.copy(
            medicines_posology = new_medicines_posologies
        )
    }

    fun changeBottomSheetState(isOpen: Boolean) {
        _state.value = state.value.copy(
            isBottomSheetOpen = isOpen
        )
    }
}