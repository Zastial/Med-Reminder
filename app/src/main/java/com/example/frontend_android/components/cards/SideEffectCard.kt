package com.example.frontend_android.components.cards

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontend_android.R
import com.example.frontend_android.components.medicineCounter
import com.example.frontend_android.data.model.Prescription
import com.example.frontend_android.data.model.SideEffect
import com.example.frontend_android.navigation.RootScreen
import com.example.frontend_android.ui.theme.Purple40
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideEffectCard(
    sideEffect: SideEffect,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = { Log.e("CLICK", "click prescripotion") }

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Effets secondaires observés le : ${sideEffect.date}",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(12.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                value = sideEffect.description,
                onValueChange = {},
                readOnly = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Purple40,
                ),
            )
        }
    }
}

@Preview(showBackground = true, )
@Composable
fun SideEffectPreview() {
    SideEffectCard(
        sideEffect = SideEffect(
            id = 1,
            description = "Je suis une description d'effet secondaire",
            medicine_id = 1,
            prescription_id = 1,
            date = LocalDate.now().toString()
        )
    )

}