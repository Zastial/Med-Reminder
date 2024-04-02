package com.example.frontend_android.ui.pages.prescription.creation_pages

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.frontend_android.R
import com.example.frontend_android.ui.components.bottomSheets.BottomSheetOperationValidation
import com.example.frontend_android.ui.components.bottomSheets.BottomSheetSurface
import com.example.frontend_android.ui.components.cards.MedicineCard
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel
import com.example.frontend_android.ui.theme.md_theme_dark_green
import com.example.frontend_android.utils.detectAllergies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecapPrescription(navController: NavController, viewModel: CreatePrescriptionViewModel) {
    val state = viewModel.state.value
    val scrollState = rememberScrollState()

    val context = LocalContext.current
    val ciMedicines = detectAllergies(viewModel.state.value.medecineAndDosage.map { it.first }, context)

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LinearProgressIndicator(
            progress = { if (!state.isBottomSheetOpen) viewModel.stepToProgress() else 1f },
            modifier = Modifier.fillMaxWidth(),
            trackColor = MaterialTheme.colorScheme.tertiary
        )

        Text(
            text = "Récapitulatif de l'ordonnance",
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        )

        if (!Uri.EMPTY.equals(viewModel.state.value.imageUri) && viewModel.state.value.imageUri != null) {
            AsyncImage(
                model = viewModel.state.value.imageUri,
                contentDescription = "Prescription image",
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .border(1.dp, Color.LightGray),
            )
        } else {
            Column(
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .border(1.dp, Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_ghost_smile),
                    contentDescription = "recap ordonnance",
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    tint = Color.LightGray,
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        val textFieldColors = TextFieldDefaults.colors(
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.LightGray,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

        Text(
            text = "Ordonnance",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .background(Color.LightGray)
                .focusProperties { canFocus = false },
            colors = textFieldColors,
            value = state.nom,
            readOnly = true,
            onValueChange = {  },
            label = {
                Text(text = "Nom")
            },
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .background(Color.LightGray)
                .focusProperties { canFocus = false },
            colors = textFieldColors,
            value = state.description,
            readOnly = true,
            onValueChange = { },
            label = {
                Text(text = "Description")
            },
        )

        Text(
            text = "Docteur",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 20.dp)
                .focusProperties { canFocus = false },
            colors = textFieldColors,
            value = state.nomDocteur,
            readOnly = true,
            onValueChange = { },
            label = {
                Text(text = "Nom")
            },
        )

        Text(
            text = "Liste des médicaments",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 5.dp),
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
        )

        viewModel.state.value.medecineAndDosage.let {
            if (it.isEmpty()) {
                Text(
                    text = "Aucun médicament",
                    modifier = Modifier.padding(16.dp, 0.dp),
                    fontSize = 15.sp,
                )
            } else {
                for (medicine in it) {
                    MedicineCard(medicine = medicine.first, ciMedicines.contains(medicine.first.name), onDelete = {}, onClick = {navController.navigate("medicine_informations_screen/${medicine.first.cis}")}, posology = medicine.second)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        BottomSheetOperationValidation(
            isSuccesfull = true,
            isOpen = state.isBottomSheetOpen,
            title = "Votre ordonnance a été ajoutée avec succès",
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