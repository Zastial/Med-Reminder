package com.example.frontend_android.ui.components.bottomSheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.frontend_android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetImportImage(
    isOpen: Boolean,
    openCamera: () -> Unit,
    openGalerie: () -> Unit,
    onDismiss: () -> Unit
) {
    if (isOpen) {
        ModalBottomSheet(
            onDismissRequest = {
                onDismiss()
            },
            modifier = Modifier
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Choisir une photo depuis", modifier = Modifier.padding(20.dp), color = Color.Gray)
                BottomSheetSurface(icon = R.drawable.ic_add_a_photo, text = "Camera", onclick = {openCamera()})
                BottomSheetSurface(icon = R.drawable.ic_gallerie, text = "Galerie", onclick = {openGalerie()})
            }
        }
    }
}