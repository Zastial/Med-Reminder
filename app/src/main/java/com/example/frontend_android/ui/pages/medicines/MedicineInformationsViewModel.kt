package com.example.frontend_android.ui.pages.medicines

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.frontend_android.ServiceBuilder
import com.example.frontend_android.data.model.dao.MedicineDao
import com.example.frontend_android.data.model.entities.Medicine
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

data class MedicineInformationsState(
    val medicine: Medicine? = null
)

@HiltViewModel
class MedicineInformationsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val medicineDao  = ServiceBuilder.buildService(MedicineDao::class.java)

    private val medicineCis: Long = checkNotNull(savedStateHandle["medicine_cis"])


    private val _state = mutableStateOf(MedicineInformationsState())
    val state: State<MedicineInformationsState> = _state

    init {
        retrieveOneMedicine(medicineCis)
    }

    fun retrieveOneMedicine(cis: Long) {
        val requestCall = medicineDao.getMedicine(cis)

        requestCall.enqueue(object : Callback<Medicine> {
            override fun onResponse(
                call: Call<Medicine>,
                response: Response<Medicine>
            ) {
                if (response.isSuccessful) {
                    val medicine = response.body()!!
                    _state.value = state.value.copy(
                        medicine = medicine,
                    )
                }

            }

            override fun onFailure(call: Call<Medicine>, t: Throwable) {
                Log.d("Error", "onError: ${t.message}")
            }

        })
    }

}