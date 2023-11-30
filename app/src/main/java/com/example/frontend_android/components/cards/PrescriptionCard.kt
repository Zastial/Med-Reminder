package com.example.frontend_android.prescription.component.PrescriptionItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontend_android.R
import com.example.frontend_android.components.medicineItem
import com.example.frontend_android.data.model.Prescription
import java.time.LocalDate

@Composable
fun PrescriptionCard(
    prescription: Prescription,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(
        )

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
                    text = prescription.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "10/11/2023",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)

                ) {
                Text(
                    modifier = Modifier
                        .weight(weight = 1f, fill = false),
                    text = prescription.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
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

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .weight(1f)

                ) {
                    val listMedic = listOf("medoc 1", "medoc 2", "medic3", "medicament 4")
                    items(listMedic){ medic ->
                        medicineItem(medicineName = medic)
                    }
                }
                Spacer(Modifier.size(8.dp))

                Row() {
                    Text(text = "Dr. ")
                    Text(text = "Louis")
                }


            }

        }
    }
}


@Preview(showBackground = true, )
@Composable
fun PrescriptionPreview() {
    PrescriptionCard(Prescription(
        0,
        "Prescription",
        "Is a long established fact that a reader will be distraction and " +
                " by the readable content of a page when looking at its layout." +
                " The point of using Lorem Ipsum is that it has a more-or-less " +
                "normal distribution of letters, as opposed to using 'Content here," +
                " content here', making it look like readable English.",
        LocalDate.now()))

}