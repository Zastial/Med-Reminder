package com.example.frontend_android.pages.prescription

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.DoctorDao
import com.example.frontend_android.data.PrescriptionDao
import com.example.frontend_android.data.model.Doctor
import com.example.frontend_android.data.model.InvalidPrescriptionException
import com.example.frontend_android.data.model.Prescription
import com.example.frontend_android.data.model.relations.PrescriptionWithRelations
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


data class CreatePrescriptionState (
    val step: Int = 0,
    val imageUri: Uri? = null,
)


@HiltViewModel
class CreatePrescriptionModel @Inject constructor(
    private val prescriptionDao: PrescriptionDao,
    private val doctorDao: DoctorDao
): ViewModel() {

    private val _state = mutableStateOf(CreatePrescriptionState())
    val state: State<CreatePrescriptionState> = _state

    @Throws(InvalidPrescriptionException::class)
    fun insertPrescription() {

        viewModelScope.launch {
            try {
                val doctor_id = doctorDao.insertDoctor(
                    Doctor(
                        id = null,
                        first_name = "",
                        last_name = "",
                        email = ""
                    )
                )

                prescriptionDao.insertPrescription(
                    Prescription(
                        id = null,
                        title = "",
                        description = "",
                        delivered_at = LocalDate.now(),
                        doctor_id = doctor_id,
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
        println(state)
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

    fun stepToProgress() : Float {
        val res = state.value.step + 1
        return res / 6f // Changer 6 par nombre de pages dynamiquement
    }


}

