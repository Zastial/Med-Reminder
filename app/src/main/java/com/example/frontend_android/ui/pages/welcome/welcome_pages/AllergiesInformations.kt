package com.example.frontend_android.ui.pages.welcome.welcome_pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontend_android.R
import com.example.frontend_android.ui.components.forms.DropDownItem
import com.example.frontend_android.ui.components.forms.MultiSelectDropdown
import com.example.frontend_android.ui.pages.welcome.ViewWelcomeModel

@Composable
fun AllergiesInformations(
    viewModel: ViewWelcomeModel
) {
    val scrollState = rememberScrollState()
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {

        IconButton(onClick = { viewModel.previousPage() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        Image(
            modifier = Modifier
                .alpha(0.5f)
                .matchParentSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Informations allergies",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                MultiSelectDropdown(
                    label="Allergies",
                    scrollState = scrollState,
                    search = state.allergies_search,
                    onSearchChange = { viewModel.changeAllergiesSearch(it) },
                    defaultSelectedItems = state.selected_allergies.map { allergy -> DropDownItem(allergy, allergy) },
                    onClearClick = { viewModel.changeAllergiesSearch("") },
                    items = state.subtances.map { substance -> DropDownItem(substance.substanceName, substance.substanceName) },
                    onItemAdded = { item ->
                        viewModel.changeSelectedAllergies(state.selected_allergies.plus(item.value))
                    },
                    onItemRemove = { item ->
                        viewModel.changeSelectedAllergies(state.selected_allergies.minus(item.value))
                    },
                )
            }

            Button(
                onClick = { viewModel.nextPage() }
            ) {
                Text(
                    modifier = Modifier.padding(48.dp, 4.dp),
                    text = "Continuer",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}