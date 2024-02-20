package com.example.frontend_android.ui.pages.user

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.frontend_android.ServiceBuilder
import com.example.frontend_android.data.model.dao.MedicineDao
import com.example.frontend_android.data.model.entities.Medicine
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

data class UserInformationsState (
    val first_name: String = "",
    val last_name: String = "",
    val email: String = "",
    val doctor_first_name: String = "",
    val doctor_last_name: String = "",
    val doctor_email: String = "",
    val selected_allergies: Set<String> = setOf(),
    val allergies_search: String = "",
    val allergies: List<String> = listOf(),
    val searching: Boolean = false,
)

@HiltViewModel
class ViewUserInformationsModel @Inject constructor(@ApplicationContext context : Context): ViewModel() {

    private val medicineDao  = ServiceBuilder.buildService(MedicineDao::class.java)

    private val sharedPreferences = context.getSharedPreferences("user_infos", Context.MODE_PRIVATE)

    private val _state = mutableStateOf(UserInformationsState())
    val state: State<UserInformationsState> = _state

    init {
        retrieveUserInfos()
        retrieveMedicines()
    }

    private fun retrieveMedicines() {
        println(_state.value.allergies_search)
        val requestCall = medicineDao.getMedicines(_state.value.allergies_search, true)

        requestCall.enqueue(object : Callback<List<Medicine>> {
            override fun onResponse(
                call: Call<List<Medicine>>,
                response: Response<List<Medicine>>
            ) {
                if (response.isSuccessful) {
                    val medicineList = response.body()!!
                    val substanceList = medicineList.map { medicine -> medicine.substanceName }
                    _state.value = state.value.copy(
                        allergies = substanceList,
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

    private fun retrieveUserInfos() {
        val userInfos = sharedPreferences.all

        _state.value = state.value.copy(
            first_name = (userInfos["first_name"] ?: "") as String,
            last_name = (userInfos["last_name"] ?: "") as String,
            email = (userInfos["email"] ?: "") as String,
            doctor_first_name = (userInfos["doctor_first_name"] ?: "") as String,
            doctor_last_name = (userInfos["doctor_last_name"] ?: "") as String,
            doctor_email = (userInfos["doctor_email"] ?: "") as String,
            selected_allergies = (userInfos["selected_allergies"] ?: setOf<String>()) as Set<String>
        )
    }

    fun handleValidation() {
        with (sharedPreferences.edit()) {
            putString("first_name", _state.value.first_name)
            putString("last_name", _state.value.last_name)
            putString("email", _state.value.email)
            putString("doctor_first_name", _state.value.doctor_first_name)
            putString("doctor_last_name", _state.value.doctor_last_name)
            putString("doctor_email", _state.value.doctor_email)
            putStringSet("allergies", _state.value.selected_allergies)
            apply()
        }

    }

    fun changeFirstName(new_first_name: String) {
        _state.value = state.value.copy(
            first_name = new_first_name
        )
    }

    fun changeLastName(new_last_name: String) {
        _state.value = state.value.copy(
            last_name = new_last_name
        )
    }

    fun changeEmail(new_email: String) {
        _state.value = state.value.copy(
            email = new_email
        )
    }

    fun changeDoctorFirstName(new_first_name: String) {
        _state.value = state.value.copy(
            doctor_first_name = new_first_name
        )
    }

    fun changeDoctorLastName(new_last_name: String) {
        _state.value = state.value.copy(
            doctor_last_name = new_last_name
        )
    }

    fun changeDoctorEmail(new_email: String) {
        _state.value = state.value.copy(
            doctor_email = new_email
        )
    }

    fun changeAllergiesSearch(new_allergies_search: String) {
        _state.value = state.value.copy(
            allergies_search = new_allergies_search
        )
        if (new_allergies_search != "") {
            retrieveMedicines()
        } else {
            changeAllergies(emptyList())
        }
    }

    fun changeSearching(new_searching: Boolean) {
        _state.value = state.value.copy(
            searching = new_searching
        )
    }

    fun changeAllergies(new_allergies: List<String>) {
        _state.value = state.value.copy(
            allergies = new_allergies
        )
    }

    fun changeSelectedAllergies(new_selected_allergy: String) {
        val new_selected_allergies: MutableList<String> = state.value.copy().selected_allergies.toMutableList()
        if (new_selected_allergies.contains(new_selected_allergy)) {
            new_selected_allergies.remove(new_selected_allergy)
        } else {
            new_selected_allergies.add(new_selected_allergy)
        }
        _state.value = state.value.copy(
            selected_allergies = new_selected_allergies.toSet()
        )
    }


}