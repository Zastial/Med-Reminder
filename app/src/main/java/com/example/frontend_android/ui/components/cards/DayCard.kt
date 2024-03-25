import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DayCard(day: String, onClick : () -> Unit, isActive : Boolean){



    OutlinedButton(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)

        ,
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