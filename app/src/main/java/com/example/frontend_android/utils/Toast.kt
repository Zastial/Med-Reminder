package com.example.frontend_android.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun showToast(message: String, duration: Int) {

    if (message.isBlank()){
        Toast.makeText(LocalContext.current, "Une action a été effectuée", duration).show()
    } else {
        Toast.makeText(LocalContext.current, message, duration).show()
    }

}
