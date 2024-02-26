package com.example.frontend_android.ui.components.bottomSheets

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.frontend_android.R

@Composable
fun BottomSheetSurface(icon : Int, text : String, onclick : () -> Unit){
    Surface(
        onClick = { onclick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Description"
            )
            Spacer(modifier = Modifier.width(width = 10.dp))
            Text(
                text = text,
                textAlign = TextAlign.Center,
            )
        }
    }
}