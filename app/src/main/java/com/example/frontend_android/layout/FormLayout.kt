package com.example.frontend_android.layout

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarValidation

@Composable
fun FormLayout(
    title: String,
    canGoBack: Boolean,
    navController: NavController,
    onValidation: () -> Unit,
    onCancellation: () -> Unit,
    Content: (@Composable () -> Unit)? = null,
) {

    Layout(
        title = title,
        canGoBack = canGoBack,
        navController = navController,
        BottomBar = {
            BottomBarValidation(
                navController = navController,
                onValidation = onValidation,
                onCancellation = onCancellation
            )
        },
    ) {
        if (Content != null) {
            Content()
        }
    }

}