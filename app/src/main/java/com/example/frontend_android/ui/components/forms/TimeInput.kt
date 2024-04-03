package com.example.frontend_android.ui.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimeInput(
    hours: Int,
    minutes: Int,
    setHours: (Int) -> Unit,
    setMinutes: (Int) -> Unit
) {

    fun changeHours(new_hours: String) {
        val value = new_hours.toIntOrNull() ?: return

        if (value > 24 || value < 0) {
            return
        }
        setHours(value)
    }

    fun changeMinutes(new_minutes: String) {
        val value = new_minutes.toIntOrNull() ?: return

        if (value > 60 || value < 0) {
            return
        }
        setMinutes(value)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            OutlinedTextField(
                modifier = Modifier.width(100.dp),
                value = hours.toString(),
                onValueChange = { changeHours(it) }
            )
            Text(text = "Heures")
        }
        
        Text(modifier = Modifier.offset(0.dp, (-12).dp), text = ":", fontSize = 32.sp)

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {

            OutlinedTextField(
                modifier = Modifier.width(100.dp),
                value = minutes.toString(),
                onValueChange = { changeMinutes(it) }

            )
            Text(text = "Minutes")

        }
    }

}