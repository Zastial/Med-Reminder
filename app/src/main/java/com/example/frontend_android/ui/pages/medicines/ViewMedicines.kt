package com.example.frontend_android.ui.pages.medicines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.frontend_android.R
import com.example.frontend_android.ui.components.cards.MedicineCard
import com.example.frontend_android.ui.components.forms.SearchTextField
import com.example.frontend_android.ui.components.layout.BaseLayout
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar

@Composable
fun ViewMedicines(
    navController: NavController,
    viewModel: MedicinesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

   BaseLayout(
       TopBar = {
           TopBar(
               navController = navController,
               title = "Médicaments",
               canGoBack = true
           )
       },
       BottomBar = {
           BottomBarNavigation(
               navController = navController,
           )
       }
   ) {
        Column(
            modifier = Modifier.padding(16.dp, 0.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SearchTextField(value = state.search, onValueChange = { viewModel.changeSearch(it) })

            if (state.medicines.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)

                ) {
                    items(state.medicines) {
                            medicine -> MedicineCard(navController = navController, medicine = medicine)
                    }
                }
            } else if (state.search == "") {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_ghost_smile),
                        contentDescription = "sad smiley",
                    )
                    Text(text = "Rechercher un médicament")
                }
            } else if (!state.searching) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_ghost),
                        contentDescription = "sad smiley",
                    )
                    Text(text = "Aucun médicament trouvé")
                }


            }
        }
   }
}