package com.example.frontend_android.ui.components.forms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontend_android.ui.theme.MedreminderTheme


@Composable
fun BtnContinue(enabled : Boolean = true, modifier : Modifier, actionText: String, onClick : () -> Unit) {
    Button(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        onClick = { onClick() }
    ) {
        Text(text = actionText)
    }
}


@Preview(showBackground = true )
@Composable
fun ButtonPreview() {
    MedreminderTheme {
        BtnContinue(actionText = "Continuer", modifier = Modifier, onClick = { })

    }
}


