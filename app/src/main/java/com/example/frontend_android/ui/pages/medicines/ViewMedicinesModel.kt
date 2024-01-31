package com.example.frontend_android.ui.pages.medicines

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.frontend_android.ServiceBuilder
import com.example.frontend_android.data.model.dao.MedicineDao
import com.example.frontend_android.data.model.Medicine
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

data class MedicinesState (
    val medicines: List<Medicine> = emptyList()
)


@HiltViewModel
class ViewMedicinesModel @Inject constructor(): ViewModel() {

    val medicineDao  = ServiceBuilder.buildService(MedicineDao::class.java)

    private val _state = mutableStateOf(MedicinesState())
    val state: State<MedicinesState> = _state

    init {
        retrieveMedicines()
    }

    private fun retrieveMedicines() {
        val requestCall = medicineDao.getMedicines()

        requestCall.enqueue(object : Callback<List<Medicine>> {
            override fun onResponse(
                call: Call<List<Medicine>>,
                response: Response<List<Medicine>>
            ) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    val medicineList = response.body()!!
                    _state.value = state.value.copy(
                        medicines = medicineList,
                    )
                }
            }

            override fun onFailure(call: Call<List<Medicine>>, t: Throwable) {
                Log.d("Error", "onError: ${t.message}")
            }
        })
    }


}