package com.example.frontend_android.pages.prescription

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.PrescriptionDao
import com.example.frontend_android.data.model.InvalidPrescriptionException
import com.example.frontend_android.data.model.Prescription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject



@HiltViewModel
class CreatePrescriptionModel @Inject constructor(
    private val prescriptionDao: PrescriptionDao,
): ViewModel() {

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
}

