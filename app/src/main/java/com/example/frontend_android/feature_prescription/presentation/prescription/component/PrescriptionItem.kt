package com.example.frontend_android.feature_prescription.presentation.prescription.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.frontend_android.feature_prescription.domain.model.Prescription

@Composable
fun PrescriptionItem(
    prescription: Prescription,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = prescription.titre,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = prescription.titre,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}