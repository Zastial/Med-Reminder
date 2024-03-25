package com.example.frontend_android.ui.components.cards

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontend_android.data.model.entities.SideEffect
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideEffectCard(
    sideEffect: SideEffect,
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
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(all = 8.dp),
                text = sideEffect.description,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                maxLines = 5,
            )
        }
    }
}

@Preview(showBackground = true)
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