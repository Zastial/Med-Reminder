package com.example.frontend_android.pages.user

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

data class UserInformationsState (
    val first_name: String = "",
    val last_name: String = "",
    val email: String = "",
    val doctor_first_name: String = "",
    val doctor_last_name: String = "",
    val doctor_email: String = "",
    val allergies: String = "",
)

@HiltViewModel
class ViewUserInformationsModel @Inject constructor(@ApplicationContext context : Context): ViewModel() {



    val sharedPreferences = context.getSharedPreferences("user_infos", Context.MODE_PRIVATE)

    private val _state = mutableStateOf(UserInformationsState())
    val state: State<UserInformationsState> = _state


    init {
        retrieveUserInfos()
    }

    private fun retrieveUserInfos() {
        val user_infos = sharedPreferences.all

        _state.value = state.value.copy(
            first_name = (user_infos["first_name"] ?: "") as String,
            last_name = (user_infos["last_name"] ?: "") as String,
            email = (user_infos["email"] ?: "") as String,
            doctor_first_name = (user_infos["doctor_first_name"] ?: "") as String,
            doctor_last_name = (user_infos["doctor_last_name"] ?: "") as String,
            doctor_email = (user_infos["doctor_email"] ?: "") as String,
            allergies = (user_infos["allergies"] ?: "") as String
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
            putString("allergies", _state.value.allergies)
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

    fun changeAllergies(new_allergies: String) {
        _state.value = state.value.copy(
            allergies = new_allergies
        )
    }


}