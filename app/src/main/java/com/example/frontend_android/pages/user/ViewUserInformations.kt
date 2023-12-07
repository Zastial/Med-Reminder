package com.example.frontend_android.pages.user
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frontend_android.components.cards.UserSectionCard
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.ui.theme.Purple40

@Composable
fun ViewUserInformations(
    navController: NavController
) {

    Scaffold (
        scaffoldState = rememberScaffoldState(),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column() {
                Column() {
                    Text(
                        text = "Vos informations",
                        style = MaterialTheme.typography.titleMedium
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "Nom")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Purple40,
                        ),
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "Prénom")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Purple40,
                        ),
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "Email")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Purple40,
                        ),
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))

                Column() {
                    Text(
                        text = "Médecin traitant",
                        style = MaterialTheme.typography.titleMedium
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "Nom")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Purple40,
                        ),
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "Prénom")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Purple40,
                        ),
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(text = "Email")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Purple40,
                        ),
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))
            }

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .width(350.dp),
                    text = "Écrivez ci-dessous vos allergies, séparées par une virgule. \n" +
                            "Si vous n’en avez pas, passez à la suite.",
                    style = MaterialTheme.typography.titleMedium,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .width(300.dp)
                        .height(150.dp),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = "Allergies")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
            }
        }
    }
}