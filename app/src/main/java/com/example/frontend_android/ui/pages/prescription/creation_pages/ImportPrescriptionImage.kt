package com.example.frontend_android.ui.pages.prescription.creation_pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionModel

@Composable
fun ImportPrescriptionImage(viewModel: CreatePrescriptionModel) {
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            viewModel.changeImageUri(uri)
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth().
            fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = viewModel.stepToProgress(),
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = "Commencez par ajouter une ordonnance",
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        )

        AsyncImage(
            model = viewModel.state.value.imageUri ?: "",
            contentDescription = "",
            modifier = Modifier
                .background(color = Color.LightGray)
                .height(500.dp)
                .clip(RectangleShape)
                .border(1.dp, Color.Black),
        )
        Button(
            onClick = { galleryLauncher.launch("image/*") },
            modifier = Modifier
                .padding(top = 16.dp)
                .height(50.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Upload picture",
                fontSize = 15.sp,
            )
        }
    }
}