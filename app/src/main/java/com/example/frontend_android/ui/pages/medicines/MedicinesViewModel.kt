package com.example.frontend_android.ui.pages.medicines

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.frontend_android.ServiceBuilder
import com.example.frontend_android.data.model.dao.MedicineDao
import com.example.frontend_android.data.model.entities.Medicine
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject




@HiltViewModel
class MedicinesViewModel @Inject constructor(): ViewModel() {

    private val medicineDao  = ServiceBuilder.buildService(MedicineDao::class.java)

    private val _state = mutableStateOf(MedicinesState())
    val state: State<MedicinesState> = _state


    private val timer = object : CountDownTimer(500, 100) {
        override fun onTick(p0: Long) {}
        override fun onFinish() {
            retrieveMedicines()
        }
    }

    private fun retrieveMedicines() {
        val requestCall = medicineDao.getMedicines(_state.value.search)

        requestCall.enqueue(object : Callback<List<Medicine>> {
            override fun onResponse(
                call: Call<List<Medicine>>,
                response: Response<List<Medicine>>
            ) {
                if (response.isSuccessful) {
                    val medicineList = response.body()!!
                    _state.value = state.value.copy(
                        medicines = medicineList,
                    )
                }
                changeSearching(false)

            }

            override fun onFailure(call: Call<List<Medicine>>, t: Throwable) {
                Log.d("Error", "onError: ${t.message}")
                changeSearching(false)
            }

        })
    }

    fun changeSearching(new_searching: Boolean) {
        _state.value = state.value.copy(
            searching = new_searching
        )
    }

    fun changeMedicines(new_medicines: List<Medicine>) {
        _state.value = state.value.copy(
            medicines = new_medicines
        )
    }

    fun changeSearch(new_search: String) {
        timer.cancel()
        _state.value = state.value.copy(
            search = new_search
        )
        if (new_search != "") {
            timer.start()
            changeSearching(true)
        } else {
            changeMedicines(emptyList())
        }
    }


}