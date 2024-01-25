package com.example.frontend_android.pages.prescription

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.frontend_android.R
import com.example.frontend_android.components.layout.BottomBarValidation
import com.example.frontend_android.components.layout.TopBar
import com.example.frontend_android.layout.BaseLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyPrescriptions(
    navController: NavController,
    uri: String?,
    viewModel: CreatePrescriptionModel = hiltViewModel()
) {

    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Ajouter une ordonnance",
                canGoBack = true
            )
        },
        BottomBar = {
            BottomBarValidation(
                navController = navController,
                onValidation = { },
                onCancellation = {})
        }
    ) {
        if (!uri.isNullOrEmpty()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uri)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .padding(4.dp)
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        } else {
            Text(text = "no uri")
        }
    }
}