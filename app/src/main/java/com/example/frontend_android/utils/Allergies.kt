package com.example.frontend_android.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.frontend_android.data.model.entities.Medicine

fun detectAllergies(medicines :  MutableList<Pair<Medicine, String>>, context : Context) : MutableList<String>{
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