package com.example.frontend_android.ui.components.cards
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontend_android.R
import com.example.frontend_android.data.model.entities.MedicinePosology
import com.example.frontend_android.data.model.entities.Prescription
import com.example.frontend_android.data.model.relations.PrescriptionWithRelations
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionCard(
    prescriptionWithRelations: PrescriptionWithRelations,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = prescriptionWithRelations.prescription.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = prescriptionWithRelations.prescription.formatDate,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

                ) {
                Text(
                    modifier = Modifier
                        .weight(weight = 1f, fill = false),
                    text = prescriptionWithRelations.prescription.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.size(12.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = "Show prescription"
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "${prescriptionWithRelations.medicine_posologies.size} médicaments")

                Row() {
                    Text(text = "Dr. ")
                    prescriptionWithRelations.prescription.doctor_last_name?.let { Text(text = it) }
                }


            }

        }
    }
}


@Preview(showBackground = true, )
@Composable
fun PrescriptionPreview() {
    PrescriptionCard(
        PrescriptionWithRelations(
            Prescription(

                0,
                "Prescription",
                "Is a long established fact " +
                        " by the readable content of a page when looking at its layout." +
                        " The point of using Lorem Ipsum is that it has a more-or-less " +
                        "normal distribution of letters, as opposed to using 'Content here," +
                        " content here', making it look like readable English.",
                LocalDate.now(),
                doctor_first_name = "Jean",
                doctor_last_name = "Dupont",
                doctor_email = "jean.dupont@medecine.com",
            ),
            listOf(
                MedicinePosology(0, "Ceci est un médicament", 0, 0),
                MedicinePosology(1, "Ceci est un médicament", 1, 0),
                MedicinePosology(2, "Ceci est un médicament", 2, 0),
            ),
            listOf()
        )
    )
}