package com.example.frontend_android.ui.pages.user
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.bottomSheets.BottomSheetOperationValidation
import com.example.frontend_android.ui.components.forms.DropDownItem
import com.example.frontend_android.ui.components.forms.MultiSelectDropdown
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarValidation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.theme.md_theme_dark_green


@Composable
fun ViewUserInformations(
    navController: NavController,
    viewModel: ViewUserInformationsModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scrollState = rememberScrollState()

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
                onValidation = { viewModel.changeBottomSheetState(true) },
                onCancellation = { navController.navigateUp() }
            )
        },
        scrollState = scrollState
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
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.last_name,
                    onValueChange = { viewModel.changeLastName(it) },
                    label = {
                        Text(text = "Nom")
                    }
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.email,
                    onValueChange = { viewModel.changeEmail(it) },
                    label = {
                        Text(text = "Email")
                    }
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
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.doctor_last_name ?: "",
                    onValueChange = { viewModel.changeDoctorLastName(it) },
                    label = {
                        Text(text = "Nom")
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.doctor_email ?: "",
                    onValueChange = { viewModel.changeDoctorEmail(it) },
                    label = {
                        Text(text = "Email")
                    }
                )


            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Allergies",
                    style = MaterialTheme.typography.titleMedium
                )

                MultiSelectDropdown(
                    label="Allergies",
                    scrollState = scrollState,
                    search = state.allergies_search,
                    onSearchChange = { viewModel.changeAllergiesSearch(it) },
                    defaultSelectedItems = state.selected_allergies.map { allergy -> DropDownItem(allergy, allergy) },
                    onClearClick = { viewModel.changeAllergiesSearch("") },
                    items = state.subtances.map { substance -> DropDownItem(substance.substanceName, substance.substanceName) },
                    onItemAdded = { item ->
                        viewModel.changeSelectedAllergies(state.selected_allergies.plus(item.value))
                    },
                    onItemRemove = { item ->
                        viewModel.changeSelectedAllergies(state.selected_allergies.minus(item.value))
                    },
                )
            }

            BottomSheetOperationValidation(
                isSuccesfull = true,
                isOpen = state.isBottomSheetOpen,
                title = "Informations mises à jour avec succès",
                description = "Vous allez être redirigé vers la page précédente",
                actionButton = {
                    Button(
                        modifier = Modifier.background(Color.Transparent).width(200.dp),
                        colors = ButtonDefaults.buttonColors(
                            md_theme_dark_green,
                        ),
                        onClick = {
                            viewModel.handleValidation()
                            navController.navigateUp()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Done",
                        )
                    }
                }
            )
        }
    }
}