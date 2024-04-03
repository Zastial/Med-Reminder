package com.example.frontend_android.ui.pages.welcome.welcome_pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import com.example.frontend_android.R
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.ui.pages.welcome.ViewWelcomeModel

@Composable
fun InformationsValidation(
    viewModel: ViewWelcomeModel,
    navController: NavController,
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {

        IconButton(onClick = { viewModel.previousPage() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        Image(
            modifier = Modifier
                .alpha(0.5f)
                .matchParentSize(),
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
                text = "Accepter les conditions",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Checkbox(
                    checked = state.accepted_condition,
                    onCheckedChange = { viewModel.changeAcceptCondition(it) }
                )
                Text(text = "En cochant cette case, vous acceptez les conditions d'utilisation de l'application MedReminder.")
            }

            Button(
                enabled = state.accepted_condition,
                onClick = {
                    viewModel.handleValidation()
                    navController.navigate(Screen.viewPrescriptions.route) {
                        popUpTo(0)
                    }
                }
            ) {
                Text(
                    modifier = Modifier.padding(48.dp, 4.dp),
                    text = "Valider mes informations",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}