package com.example.frontend_android.camera

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.io.File
import java.util.concurrent.ExecutorService


class CameraStates {
    var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)
    lateinit var outputDirectory: File
    lateinit var cameraExecutor: ExecutorService
    lateinit var photoUri: Uri


    fun handleImageCapture(uri: Uri) {
        shouldShowCamera.value = false
        photoUri = uri
        shouldShowPhoto.value = true

    }

    companion object {
        @Volatile
        private var instance: CameraStates? = null

        fun getInstance(): CameraStates {
            return instance ?: synchronized(this) {
                instance ?: CameraStates().also { instance = it }
            }
        }
    }
}