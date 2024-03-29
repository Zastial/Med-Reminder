
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontend_android.ui.pages.notification.add_edit_notification.DaysOfWeek
import com.example.frontend_android.ui.pages.notification.add_edit_notification.display


@Composable
fun Week(modifier : Modifier, dayScheduled : MutableList<DaysOfWeek>, onSelectDay : (DaysOfWeek) -> Unit) {


    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Log.e("DAY items", dayScheduled.toString())
        for (day in DaysOfWeek.values()) {
            DayCard(day = day, onClick = { onSelectDay(it) }, isActive = dayScheduled.contains(day))
        }
    }

}


@Composable
fun DayCard(day: DaysOfWeek, onClick : (DaysOfWeek) -> Unit, isActive : Boolean){

    var strokeColor = MaterialTheme.colorScheme.outline
    var textColor = MaterialTheme.colorScheme.outline
    var backgroundColor = MaterialTheme.colorScheme.surface

    if (isActive) {
        strokeColor = MaterialTheme.colorScheme.primary
        textColor = MaterialTheme.colorScheme.onPrimary
        backgroundColor =  MaterialTheme.colorScheme.primary
    }

    TextButton(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, color = strokeColor),
                shape = CircleShape
            )
            .background(color = backgroundColor, shape = CircleShape)
            .size(40.dp),
        onClick = { onClick(day) },

    ) {
        Text(text = day.display(), color = textColor)
    }

}





@Preview
@Composable
fun dayCardPreview() {
    DayCard(DaysOfWeek.LUNDI, {}, false)
}