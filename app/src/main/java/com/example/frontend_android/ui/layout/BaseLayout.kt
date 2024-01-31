package com.example.frontend_android.ui.layout

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
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

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        scaffoldState = scaffoldState,
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
    ) {
        Surface(modifier = modifier.padding(8.dp, 16.dp).padding(it)) {
            if (Content != null) {
                Content()
            }
        }
    }
}