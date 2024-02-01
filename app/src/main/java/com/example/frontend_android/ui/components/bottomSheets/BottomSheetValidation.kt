package com.example.frontend_android.ui.components.bottomSheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontend_android.ui.components.forms.buttons.BtnContinue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetOperationValidation(isSuccesfull: Boolean, isOpen : Boolean, title: String, description: String, actionButton : @Composable () -> Unit) {
    var isSheetOpen by rememberSaveable { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    if (isOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            },
            modifier = Modifier
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(text = title, textAlign = TextAlign.Center)
                Text(text = description, textAlign = TextAlign.Center)
                actionButton()
            }
        }
    }
}

@Preview(showBackground = true )
@Composable
fun DefaultBottomSheetPreview() {
    BottomSheetOperationValidation(
        isSuccesfull = true,
        isOpen = false,
        title = "Votre ordonnance à été correctement enregistrée",
        description = "L'opération c'est terminée avec succès",
        actionButton = { BtnContinue(actionText = "Continuer", onClick = { }) }
    )
}