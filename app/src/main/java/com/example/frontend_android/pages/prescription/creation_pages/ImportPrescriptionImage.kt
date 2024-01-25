package com.example.frontend_android.pages.prescription.creation_pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.frontend_android.pages.prescription.CreatePrescriptionModel

@Composable
fun ImportPrescriptionImage(viewModel: CreatePrescriptionModel) {

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            viewModel.changeImageUri(uri)
            viewModel.nextPage()
        }
    }



    Button(onClick = { galleryLauncher.launch("image/*") }) {
        Text(text = "Upload picture")
    }

}