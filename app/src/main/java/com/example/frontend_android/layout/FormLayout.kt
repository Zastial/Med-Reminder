package com.example.frontend_android.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarValidation

@Composable
fun FormLayout(
    title: String,
    canGoBack: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier,
    onValidation: () -> Unit,
    onCancellation: () -> Unit,
    Content: (@Composable () -> Unit)? = null,
) {

    BaseLayout(
        title = title,
        canGoBack = canGoBack,
        navController = navController,
        modifier = modifier,
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