package com.example.frontend_android.ui.pages.medicines

import com.example.frontend_android.data.model.entities.Medicine

data class MedicinesState(
    val medicines: List<Medicine> = emptyList(),
    val search: String = "",
    val searching: Boolean = false,
)
