package com.example.frontend_android.ui.pages.prescription.creation_pages

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel
import com.example.frontend_android.ui.theme.LightGrey
import com.example.frontend_android.ui.theme.Purple40
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun FillPrescriptionInfos(viewModel: CreatePrescriptionViewModel) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = viewModel.stepToProgress(),
            modifier = Modifier.fillMaxWidth(),
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
            val nom = remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 20.dp),
                value = nom.value,
                onValueChange = { viewModel.changeNom(nom.value) },
                label = {
                    Text(text = "Nom de l'ordonnance")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Purple40,
                ),
            )

            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp
            val description = remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 1.5f),
                value = description.value,
                onValueChange = { viewModel.changeDescription(description.value) },
                label = {
                    Text(text = "Description supplémentaire")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Purple40,
                ),
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
            containerColor = LightGrey,
            contentColor = Color.Black
        ),
        shape = RectangleShape,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = "Date",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(10.dp))
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight(),
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                color = Color.Black,
                fontSize = 18.sp,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShowCalendarPreview() {
    ShowCalendar(hiltViewModel())
}
