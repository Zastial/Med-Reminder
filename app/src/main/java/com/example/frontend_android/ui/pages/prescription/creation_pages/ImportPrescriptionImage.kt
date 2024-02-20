package com.example.frontend_android.ui.pages.prescription.creation_pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.frontend_android.R
import com.example.frontend_android.ui.pages.prescription.CreatePrescriptionViewModel

@Composable
fun ImportPrescriptionImage(
    viewModel: CreatePrescriptionViewModel,
) {
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            viewModel.changeImageUri(uri)
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
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

        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        if (!Uri.EMPTY.equals(viewModel.state.value.imageUri) && viewModel.state.value.imageUri != null) {
            AsyncImage(
                model = viewModel.state.value.imageUri,
                contentDescription = "Prescription image",
                modifier = Modifier
                    .height(screenHeight / 1.7f)
                    .clip(RectangleShape)
                    .border(1.dp, Color.LightGray)
                    .clickable { galleryLauncher.launch("image/*") },
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 1.7f)
                    .clip(RectangleShape)
                    .border(1.dp, Color.LightGray)
                    .clickable { galleryLauncher.launch("image/*") },
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
                .padding(top = 20.dp),
        ) {
            Button(
                onClick = { galleryLauncher.launch("image/*") },
                modifier = Modifier
                    .width(250.dp)
                    .padding(6.dp)
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
                    .width(250.dp)
                    .padding(6.dp),
                colors = ButtonDefaults.buttonColors(Color.Red),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete image"
                )
            }
        }
    }
}