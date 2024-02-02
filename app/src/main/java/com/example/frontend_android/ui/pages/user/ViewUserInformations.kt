package com.example.frontend_android.ui.pages.user
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
<<<<<<< Updated upstream
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
=======
>>>>>>> Stashed changes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
<<<<<<< Updated upstream
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.theme.Purple40
=======
>>>>>>> Stashed changes

@Composable
fun ViewUserInformations(
    navController: NavController
) {

    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Infomations utilisateur",
                canGoBack = true,
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController,
            )
        },
    ) {
        Column(
            modifier = Modifier.padding(24.dp, 0.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column() {
                Text(
                    text = "Vos informations",
                    style = MaterialTheme.typography.titleMedium
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = "Nom")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = "Prénom")
                    },
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
<<<<<<< Updated upstream
                    value = "",
                    onValueChange = {},
=======
                    value = state.last_name,
                    onValueChange = { viewModel.changeLastName(it) },
                    label = {
                        Text(text = "Nom")
                    },
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email,
                    onValueChange = { viewModel.changeEmail(it) },
>>>>>>> Stashed changes
                    label = {
                        Text(text = "Email")
                    },
                )
            }

            Column() {
                Text(
                    text = "Médecin traitant",
                    style = MaterialTheme.typography.titleMedium
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = "Nom")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = "Prénom")
                    },
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
<<<<<<< Updated upstream
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = "Email")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
            }

            Column() {
                Text(
                    modifier = Modifier
                        .width(350.dp),
                    text = "Écrivez ci-dessous vos allergies, séparées par une virgule. \n" +
                            "Si vous n’en avez pas, passez à la suite.",
                    style = MaterialTheme.typography.titleMedium,
                )
                OutlinedTextField(
                    modifier = Modifier.height(150.dp).fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = "Allergies")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Purple40,
                    ),
=======
                    value = state.doctor_last_name ?: "",
                    onValueChange = { viewModel.changeDoctorLastName(it) },
                    label = {
                        Text(text = "Nom")
                    },
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.doctor_email ?: "",
                    onValueChange = { viewModel.changeDoctorEmail(it) },
                    label = {
                        Text(text = "Email")
                    },
>>>>>>> Stashed changes
                )
            }
        }


    }
}