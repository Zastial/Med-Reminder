package com.example.frontend_android.ui.pages.prescription

import android.content.ContentValues
import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.frontend_android.camera.CameraStates
import com.example.frontend_android.camera.CameraView
import com.example.frontend_android.camera.PhotoModule
import java.io.IOException
import java.util.concurrent.Executors

@Composable
fun ViewCameraScreen(navController : NavController, viewModel: CreatePrescriptionViewModel) {
    val pm = PhotoModule(context = LocalContext.current)
    val camStates = CameraStates.getInstance()
    camStates.cameraExecutor = Executors.newSingleThreadExecutor()
    camStates.outputDirectory = pm.getOutputDirectory()

    if (camStates.shouldShowCamera.value) {
        CameraView(
            outputDirectory = camStates.outputDirectory,
            executor = camStates.cameraExecutor,
            onImageCaptured = camStates::handleImageCapture,
            onError = {}
        )
}
    if (camStates.shouldShowPhoto.value) {
        viewModel.changeImageUri(camStates.photoUri)
        camStates.shouldShowPhoto.value = false
        saveImageToGallery(LocalContext.current, camStates.photoUri)
        viewModel.changeStep(0)
    }
}

fun saveImageToGallery(context: Context, uri: Uri) {
    val contentResolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "image_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }

    // Insérer l'image dans la galerie
    val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    try {
        contentResolver.openOutputStream(imageUri!!)?.use { outputStream ->
            contentResolver.openInputStream(uri)?.use { inputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        // Rafraîchir la galerie pour afficher la nouvelle image
        MediaScannerConnection.scanFile(
            context,
            arrayOf(imageUri.path),
            arrayOf("image/jpeg"),
            null
        )
    } catch (e: IOException) {
        e.printStackTrace()
    }
}