package com.example.frontend_android.ui.pages.prescription.creation_pages

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.frontend_android.R
import com.example.frontend_android.camera.CameraStates
import com.example.frontend_android.ui.components.bottomSheets.BottomSheetImportImage
import com.example.frontend_android.ui.components.bottomSheets.BottomSheetSurface
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportPrescriptionImage(navController : NavController, viewModel: CreatePrescriptionViewModel) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val heightCustom = if (screenHeight >= 800.dp) {
        screenHeight / 1.7f
    } else if (screenHeight < 800.dp && screenHeight >= 660.dp) {
        screenHeight / 1.9f
    } else {
        screenHeight / 2.0f
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                viewModel.changeImageUri(uri)
            }
        }
    val camStates = CameraStates.getInstance()
    val context = LocalContext.current
    var isCameraPermission by remember {
        mutableStateOf(
            checkPermissionFor(
                Manifest.permission.CAMERA,
                context
            )
        )
    }

    val authorizationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                isCameraPermission = true
                camStates.shouldShowCamera.value = true
                viewModel.changeStep(6) // Display the camera step
                viewModel.changeBottomSheetBool(false)
            }
        })

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = viewModel.stepToProgress(),
            modifier = Modifier.fillMaxWidth(),
            trackColor = MaterialTheme.colorScheme.tertiary
        )

        Text(
            text = "Commencez par ajouter une ordonnance",
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
                    .height(heightCustom)
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .border(1.dp, Color.LightGray)
                    .clickable { viewModel.changeBottomSheetBool(true) },
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightCustom)
                    .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                    .border(1.dp, Color.LightGray)
                    .clickable { viewModel.changeBottomSheetBool(true) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.prescription_svgrepo_com),
                    contentDescription = "Ajouter une ordonnance",
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp),
                    tint = Color.LightGray,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.changeBottomSheetBool(true) },
                modifier = Modifier
                    .fillMaxWidth(0.7f),
            ) {
                Text(
                    text = "Importer image",
                    fontSize = 15.sp,
                )
            }

            Button(
                onClick = {
                    viewModel.resetState()
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                colors = ButtonDefaults.buttonColors(Color.Red),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete image"
                )
            }
        }
    }

    BottomSheetImportImage(
        isOpen = viewModel.state.value.isBottomSheetOpen,
        openCamera = {
            if (!isCameraPermission) {
                authorizationLauncher.launch(Manifest.permission.CAMERA)
            } else {
                camStates.shouldShowCamera.value = true
                viewModel.changeStep(6) // Display the camera step
                viewModel.changeBottomSheetBool(false)
            }
        },
        openGalerie = {
            galleryLauncher.launch("image/*")
            viewModel.changeBottomSheetBool(false)
        },
        onDismiss = { viewModel.changeBottomSheetBool(!viewModel.state.value.isBottomSheetOpen) }
        )
}

fun checkPermissionFor(permission : String, context : Context) = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED