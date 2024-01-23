package com.example.frontend_android.pages.user

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
import com.example.frontend_android.components.cards.SideEffectCard

@Composable
fun ViewUserSideEffectsHistory(
    navController: NavController,
    viewModel: ViewSideEffectsHistoryModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold (
        scaffoldState = rememberScaffoldState(),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
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