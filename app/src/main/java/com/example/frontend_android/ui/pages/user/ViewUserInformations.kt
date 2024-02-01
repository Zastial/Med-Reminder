package com.example.frontend_android.ui.pages.user
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.pages.user.ViewUserInformationsModel
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarValidation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewUserInformations(
    navController: NavController,
    viewModel: ViewUserInformationsModel = hiltViewModel()
) {

    val state = viewModel.state.value

    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Infomations utilisateur",
                canGoBack = true,
            )
        },
        BottomBar = {
            BottomBarValidation(
                navController = navController,
                onValidation = { viewModel.handleValidation() },
                onCancellation = {}
            )
        },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column() {
                Text(
                    text = "Vos informations",
                    style = MaterialTheme.typography.titleMedium
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.first_name,
                    onValueChange = { viewModel.changeFirstName(it) },
                    label = {
                        Text(text = "Prénom")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.last_name,
                    onValueChange = { viewModel.changeLastName(it) },
                    label = {
                        Text(text = "Nom")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Purple40,
                    ),
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email,
                    onValueChange = { viewModel.changeEmail(it) },
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
                    text = "Médecin traitant",
                    style = MaterialTheme.typography.titleMedium
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.doctor_first_name ?: "",
                    onValueChange = { viewModel.changeDoctorFirstName(it) } ,
                    label = {
                        Text(text = "Prénom")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.doctor_last_name ?: "",
                    onValueChange = { viewModel.changeDoctorLastName(it) },
                    label = {
                        Text(text = "Nom")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.doctor_email ?: "",
                    onValueChange = { viewModel.changeDoctorEmail(it) },
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
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    value = state.allergies,
                    onValueChange = { viewModel.changeAllergies(it) },
                    label = {
                        Text(text = "Allergies")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
            }
        }


    }
}