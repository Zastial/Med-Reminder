package com.example.frontend_android.layout


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.components.layout.BottomBarNavigation
import com.example.frontend_android.components.layout.TopBar

@Composable
fun PageLayout(
    title: String,
    canGoBack: Boolean,
    navController: NavController,
    Content: (@Composable () -> Unit)? = null,
) {

    Layout(
        title = title,
        canGoBack = canGoBack,
        navController = navController,
        BottomBar = {
            BottomBarNavigation(navController = navController)
        },
    ) {
        if (Content != null) {
            Content()
        }
    }
}


@Preview
@Composable
fun PageLayoutPreview() {
    PageLayout(title = "Test title", canGoBack = true, navController = rememberNavController()) {
        Text(text = "Test content")
    }
}