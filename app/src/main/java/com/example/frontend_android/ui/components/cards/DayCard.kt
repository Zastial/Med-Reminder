
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frontend_android.ui.pages.notification.add_edit_notification.DaysOfWeek


@Composable
fun Week(onSelectDay : () -> Unit) {
    for (day in DaysOfWeek.values()) {
        when (day) {
            DaysOfWeek.LUNDI -> DayCard(day= "L", onClick= {onSelectDay()}, isActive = false)
            DaysOfWeek.MARDI -> DayCard(day= "M", onClick= {onSelectDay()}, isActive = false)
            DaysOfWeek.MERCREDI -> DayCard(day= "M", onClick= {onSelectDay()}, isActive = false)
            DaysOfWeek.JEUDI -> DayCard(day= "J", onClick= {onSelectDay()}, isActive = false)
            DaysOfWeek.VENDREDI -> DayCard(day= "V", onClick= {onSelectDay()}, isActive = false)
            DaysOfWeek.SAMEDI -> DayCard(day= "S", onClick= {onSelectDay()}, isActive = false)
            DaysOfWeek.DIMANCHE -> DayCard(day= "D", onClick= {onSelectDay()}, isActive = false)
        }
    }
}


@Composable
fun DayCard(day: String, onClick : () -> Unit, isActive : Boolean){

    val modifier =
        if (isActive)
            Modifier
        else
            Modifier

    OutlinedButton(
        modifier = modifier.size(55.dp),
        onClick = { onClick() }

    ){
        Text(text = day)
    }

}



@Preview
@Composable
fun dayCardPreview() {
    DayCard("T", {}, true)
}