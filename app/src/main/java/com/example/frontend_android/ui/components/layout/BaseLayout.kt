package com.example.frontend_android.ui.components.layout

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BaseLayout(
    modifier: Modifier = Modifier,
    scrollState: ScrollState? = null,
    TopBar: @Composable () -> Unit,
    BottomBar: @Composable () -> Unit,
    Content: (@Composable () -> Unit)? = null,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val scrollModifier = if (scrollState !== null) modifier.verticalScroll(scrollState) else modifier

    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
    ) {
        Box(modifier = scrollModifier
            .fillMaxHeight()
            .padding(it)
            .padding(8.dp, 16.dp)
        ) {
            if (Content != null) {
                Content()
            }
        }
    }
}