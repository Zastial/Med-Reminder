package com.example.frontend_android.ui.pages.welcome.welcome_pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontend_android.R
import com.example.frontend_android.ui.pages.welcome.ViewWelcomeModel

@Composable
fun DoctorInformations(
    viewModel: ViewWelcomeModel
) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {

        IconButton(onClick = { viewModel.previousPage() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        Image(
            modifier = Modifier.alpha(0.5f).matchParentSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Informations médecin traitant",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedTextField(
                    value = state.doctor_first_name,
                    onValueChange = { viewModel.changeDoctorFirstName(it) },
                    label = {
                        Text(text = "Prénom")
                    }
                )
                OutlinedTextField(
                    value = state.doctor_last_name,
                    onValueChange = { viewModel.changeDoctorLastName(it) },
                    label = {
                        Text(text = "Nom")
                    }
                )

                OutlinedTextField(
                    value = state.doctor_email,
                    onValueChange = { viewModel.changeDoctorEmail(it) },
                    label = {
                        Text(text = "Email")
                    }
                )
            }

            Button(
                onClick = { viewModel.nextPage() }
            ) {
                Text(
                    modifier = Modifier.padding(48.dp, 4.dp),
                    text = "Continuer",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}