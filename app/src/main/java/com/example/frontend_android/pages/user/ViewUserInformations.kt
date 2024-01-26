package com.example.frontend_android.pages.user
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.frontend_android.components.layout.BottomBarNavigation
import com.example.frontend_android.components.layout.TopBar
import com.example.frontend_android.layout.BaseLayout
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.ui.theme.Purple40

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
            verticalArrangement = Arrangement.spacedBy(20.dp)
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Purple40,
                    ),
                )
            }
        }


    }
}