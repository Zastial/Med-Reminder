package com.example.frontend_android.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontend_android.ui.theme.disabledColor
import com.example.frontend_android.ui.theme.primaryColor


@Composable
fun btnContinue(enabled : Boolean = true, actionText: String) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = primaryColor, disabledContainerColor = disabledColor ),
        onClick = { }
    ) {
        Text(text = actionText)
    }
}





@Preview(showBackground = true )
@Composable
fun buttonPreview() {
    btnContinue(actionText = "Continuer")
}