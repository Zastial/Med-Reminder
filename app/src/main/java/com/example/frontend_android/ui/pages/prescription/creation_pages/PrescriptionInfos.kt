package com.example.frontend_android.ui.pages.prescription.creation_pages

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun FillPrescriptionInfos(viewModel: CreatePrescriptionViewModel) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = viewModel.stepToProgress(),
            modifier = Modifier.fillMaxWidth(),
            trackColor = MaterialTheme.colorScheme.tertiary
        )

        Text(
            text = "Saisissez un nom et une description",
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        )

        Column {
            Text(
                text = "Date de délivrance",
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
            )
            ShowCalendar(viewModel)

            Text(
                text = "Informations de l'ordonnance",
                modifier = Modifier.padding(top = 20.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 20.dp),
                value = state.nom,
                onValueChange = { viewModel.changeNom(it) },
                label = {
                    Text(text = "Nom de l'ordonnance")
                },
            )

            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 1.5f),
                value = state.description,
                onValueChange = { viewModel.changeDescription(it) },
                label = {
                    Text(text = "Description supplémentaire")
                },
            )
        }
    }
}

@Composable
fun ShowCalendar(viewModel: CreatePrescriptionViewModel) {
    val context = LocalContext.current
    var date = viewModel.state.value.date

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            date = LocalDate.of(year, month, dayOfMonth)
            viewModel.changeDate(date)
        },
        date.year,
        date.monthValue,
        date.dayOfMonth
    )

    // Set the max date to today
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

    // Import date into the viewModel
    viewModel.changeDate(date)

    Button(
        onClick = {
            datePickerDialog.show()
        },
        modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            .padding(top = 5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        shape = RoundedCornerShape(5.dp),
    ) {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Date",
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.width(10.dp))
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight(),
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShowCalendarPreview() {
    ShowCalendar(hiltViewModel())
}
