package com.example.frontend_android.ui.pages.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.ui.components.cards.SideEffectCard
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.layout.BaseLayout

@Composable
fun ViewUserSideEffectsHistory(
    navController: NavController,
    viewModel: ViewSideEffectsHistoryModel = hiltViewModel()
) {
    val state = viewModel.state.value

    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Effects secondaires",
                canGoBack = true,
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController,
            )
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.sideEffects) { sideEffects -> SideEffectCard(sideEffect = sideEffects) }
            }
        }
    }

}