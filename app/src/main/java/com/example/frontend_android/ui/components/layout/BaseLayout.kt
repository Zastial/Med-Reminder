package com.example.frontend_android.ui.components.layout

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun BaseLayout(
    modifier: Modifier = Modifier,
    TopBar: @Composable () -> Unit,
    BottomBar: @Composable () -> Unit,
    Content: (@Composable () -> Unit)? = null
) {
    val snackbarHostState = remember { SnackbarHostState() }
    
    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
    ) {
        Surface(modifier = modifier
            .padding(8.dp, 16.dp)
            .padding(it)) {
            if (Content != null) {
                Content()
            }
        }
    }
}