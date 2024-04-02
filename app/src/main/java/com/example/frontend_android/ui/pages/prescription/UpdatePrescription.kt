package com.example.frontend_android.ui.pages.prescription

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.frontend_android.R
import com.example.frontend_android.components.layout.TopBarPrescriptionNavigation
import com.example.frontend_android.ui.components.bottomSheets.BottomSheetOperationValidation
import com.example.frontend_android.ui.components.cards.MedicineCard
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarValidation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.theme.md_theme_common_primaryWarning
import com.example.frontend_android.ui.theme.md_theme_dark_green
import com.example.frontend_android.utils.detectAllergies
import java.time.format.DateTimeFormatter


@Composable
fun UpdatePrescription(
    navController: NavController,
    viewModel: UpdatePrescriptionViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scrollState = rememberScrollState()

    val context = LocalContext.current
    val ciMedicines = detectAllergies(state.medicines_posology.map { it.first }, context)


    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Ordonnance du ${
                    state.date.format(
                        DateTimeFormatter.ISO_LOCAL_DATE
                    )
                }",
                canGoBack = true,
            )
        },
        BottomBar = {
            BottomBarValidation(
                navController = navController,
                onValidation = {
                    viewModel.changeBottomSheetState(true)
                    viewModel.updatePrescription()
               },
                onCancellation = {}
            )
        }
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Mise à jour de l'ordonnance",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.title,
                    onValueChange = { viewModel.changeTitle(it) },
                    label = {
                        Text(text = "Nom")
                    },
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.description,
                    onValueChange = { viewModel.changeDescription(it) },
                    label = {
                        Text(text = "Description")
                    },
                )

                Text(
                    text = "Docteur",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left,
                    fontSize = 15.sp,
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.doctor_email,
                    onValueChange = { viewModel.changeDoctorEmail(it) },
                    label = {
                        Text(text = "Email")
                    },
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.doctor_name,
                    onValueChange = { viewModel.changeDoctorName(it) },
                    label = {
                        Text(text = "Nom")
                    },
                )

                Text(
                    text = "Liste des médicaments",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 5.dp),
                    textAlign = TextAlign.Left,
                    fontSize = 15.sp,
                )

                state.medicines_posology.let {
                    if (it.isEmpty()) {
                        Text(
                            text = "Aucun médicament",
                            modifier = Modifier.padding(16.dp, 0.dp),
                            fontSize = 15.sp,
                        )
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_warning),
                                contentDescription = "Description",
                                tint = md_theme_common_primaryWarning
                            )
                            Spacer(modifier = Modifier.width(width = 10.dp))
                            if (ciMedicines.size == 1) {
                                Text(
                                    text = "Vous avez ${ciMedicines.size} médicament en contre indication avec vos allergies",
                                    textAlign = TextAlign.Left,
                                )
                            } else {
                                Text(
                                    text = "Vous avez ${ciMedicines.size} médicaments en contre indication avec vos allergies",
                                    textAlign = TextAlign.Left,
                                )
                            }
                        }
                        for (medicine_posology in it) {
                            MedicineCard(
                                medicine = medicine_posology.first,
                                hasWarning = ciMedicines.contains(medicine_posology.first.name),
                                onClick = {},
                                onDelete = {},
                                posology = medicine_posology.second,
                            )
                        }
                    }
                }
            }
        }

        BottomSheetOperationValidation(
            isSuccesfull = true,
            isOpen = state.isBottomSheetOpen,
            title = "Votre ordonnance a été modifiée avec succès",
            description = "Vous allez être redirigé vers la liste des ordonnances",
            actionButton = {
                Button(
                    modifier = Modifier.background(Color.Transparent).width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        md_theme_dark_green,
                    ),
                    onClick = {
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