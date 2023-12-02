package com.example.frontend_android.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun counterChips(number : Int) {
    
    Text(text = "+ $number", Modifier.border(width =2.dp, color= Color.Black, shape = CircleShape))
}


@Preview
@Composable
fun CounterChipsPreview()
{
    counterChips(number = 3)
}