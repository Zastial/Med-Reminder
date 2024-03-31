package com.example.frontend_android.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.frontend_android.data.model.entities.Medicine

fun detectAllergies(context : Context, medicines :  MutableList<Pair<Medicine, String>>) : MutableList<String>{
    val sharedPreferences = context.getSharedPreferences("user_infos", Context.MODE_PRIVATE)
    var userAllergies = sharedPreferences.all["allergies"] as Set<String>
    var ciMedicines = mutableListOf <String>()

    medicines.forEach { medecine ->
        if (userAllergies.contains(medecine.first.substanceName)){
            ciMedicines.add(medecine.first.name)
        }
    }

    return ciMedicines
}

fun detectAllergy(context : Context, medicine : Medicine) : Boolean{
    val sharedPreferences = context.getSharedPreferences("user_infos", Context.MODE_PRIVATE)
    var userAllergies = sharedPreferences.all["allergies"] as Set<String>

    return userAllergies.contains(medicine.substanceName)
}