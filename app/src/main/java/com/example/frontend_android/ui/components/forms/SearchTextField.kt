package com.example.frontend_android.ui.components.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SearchTextField(value: String, onValueChange: (value: String) -> Unit, placeholder: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            maxLines = 1,
            singleLine = true,
            prefix = {
                Icon(
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }
        )
    }
}


@Preview
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(value = "", onValueChange = {}, placeholder = "")
}