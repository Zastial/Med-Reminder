package com.example.frontend_android.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.frontend_android.data.model.entities.Medicine

fun detectAllergies(medicines :  List<Medicine>, context : Context) : MutableList<String>{
    val sharedPreferences = context.getSharedPreferences("user_infos", Context.MODE_PRIVATE)
    val userAllergies = sharedPreferences.all["allergies"] as Set<String>
    val ciMedicines = mutableListOf<String>()

    medicines.forEach { medecine ->
        if (userAllergies.contains(medecine.substanceName)){
            ciMedicines.add(medecine.name)
        }
    }

    return ciMedicines
}

fun detectAllergy(context : Context, medicine : Medicine) : Boolean{
    val sharedPreferences = context.getSharedPreferences("user_infos", Context.MODE_PRIVATE)
    var userAllergies = sharedPreferences.all["allergies"] as Set<String>

    return userAllergies.contains(medicine.substanceName)
}