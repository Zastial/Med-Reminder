package com.example.frontend_android.pages.prescription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.PrescriptionDao
import com.example.frontend_android.data.model.InvalidPrescriptionException
import com.example.frontend_android.data.model.Prescription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.jvm.Throws

@HiltViewModel
class CreatePrescriptionModel @Inject constructor(
    private val prescriptionDao: PrescriptionDao
): ViewModel() {




    @Throws(InvalidPrescriptionException::class)
    fun insertPrescription(prescription: Prescription) {
        if (prescription.title.isBlank()) {
            throw InvalidPrescriptionException("The title must not be empty")
        }

        viewModelScope.launch {
            try {
                prescriptionDao.insert(prescription)
            } catch (e: InvalidPrescriptionException) {}
        }
    }
}

