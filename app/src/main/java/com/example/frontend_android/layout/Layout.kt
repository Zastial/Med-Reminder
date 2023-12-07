package com.example.frontend_android.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.frontend_android.components.layout.BottomBarNavigation
import com.example.frontend_android.components.layout.TopBar

@Composable
fun Layout(
    title: String,
    canGoBack: Boolean,
    navController: NavController,
    BottomBar: @Composable () -> Unit,
    Content: (@Composable () -> Unit)? = null
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight()
        ) {
            TopBar(title = title, canGoBack = canGoBack, navController = navController)

            Surface(modifier = Modifier.weight(1f)) {
                if (Content != null) {
                    Content()
                }
            }

            BottomBar()


        }
    }

}