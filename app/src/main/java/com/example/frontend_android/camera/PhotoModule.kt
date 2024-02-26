package com.example.frontend_android.camera

import android.content.Context
import com.example.frontend_android.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File

class PhotoModule (@ApplicationContext context : Context) {
    private val _context = context

    fun getOutputDirectory(): File {
        val mediaDir = this._context.externalMediaDirs.firstOrNull()?.let {
            File(it, this._context.resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else this._context.filesDir
    }
}