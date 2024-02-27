package com.example.frontend_android.ui.pages.welcome.welcome_pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontend_android.R
import com.example.frontend_android.ui.pages.welcome.ViewWelcomeModel

@Composable
fun Hello(
    viewModel: ViewWelcomeModel
) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier.alpha(0.5f).matchParentSize(),
            painter = painterResource(R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier.padding(24.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = context.resources.getString(R.string.app_name),
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.medicine_illustration),
                contentDescription = "illustration",
                contentScale = ContentScale.FillWidth,
            )

            Button(
                onClick = { viewModel.nextPage() }
            ) {
                Text(
                    modifier = Modifier.padding(48.dp, 4.dp),
                    text = "Commencer",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}