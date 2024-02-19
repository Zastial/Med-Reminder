package com.example.frontend_android.ui.components.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontend_android.utils.FormatToStringHour
import com.example.frontend_android.utils.FormatToStringMinutes


@Composable
fun AlarmCard(
    modifier: Modifier,
    hour: Int,
    minutes: Int,
    isActive: Boolean,
    dayOfWeek: String,
    changeAlarmState: (state : Boolean) -> Unit
) {
    val isChecked by remember { mutableStateOf(isActive) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 26.dp)
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            //hour
            Row() {
                Text(text = hour.FormatToStringHour())
                Text(text = ":")
                Text(text = minutes.FormatToStringMinutes())
            }

            //date
            DayOfWeek(dayOfWeek)

            //is active

            Switch(checked = isChecked, onCheckedChange = { changeAlarmState(!isChecked) } )
        }
    }
}




@Composable
fun DayOfWeek(dayOfWeek: String){

    Row(horizontalArrangement = Arrangement.Center ) {
        //convert gson to day item with L  M  M J V S D
    }

}


@Composable
fun DayItem(day: String, isActive: Boolean) {

    Column(modifier = Modifier.padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        var color = MaterialTheme.colorScheme.onSurface
        if (isActive) {
            color = MaterialTheme.colorScheme.primary
        }

        Canvas(modifier = Modifier.size(6.dp), onDraw = {
            drawCircle(color = color)
        })
        Text(text = day, color = color)
    }
}









@Preview(
    showBackground = true
)
@Composable
fun AlarmCardPreview() {
    AlarmCard(modifier = Modifier, hour = 0, minutes= 0, isActive = false, dayOfWeek = "", changeAlarmState = { })
}


@Preview(
    showBackground = true
)
@Composable
fun DayItemPreview() {
    DayItem("L", false)
}
