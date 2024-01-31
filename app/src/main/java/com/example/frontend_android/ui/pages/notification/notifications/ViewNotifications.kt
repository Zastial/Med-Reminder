package com.example.frontend_android.ui.pages.notification.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.navigation.Screen
import com.example.frontend_android.ui.components.layout.BottomBarNavigation
import com.example.frontend_android.ui.components.layout.TopBar
import com.example.frontend_android.ui.layout.BaseLayout
import com.example.frontend_android.ui.theme.MedreminderTheme

@Composable
fun ViewNotifications(
    navController: NavController
) {
    //val viewModelNotification = hiltViewModel<>()
    BaseLayout(
        TopBar = {
            TopBar(
                navController = navController,
                title = "Notifications",
                canGoBack = false
            )
        },
        BottomBar = {
            BottomBarNavigation(
                navController = navController
            )
        }
    ) {
        Column {
            Text(text = "Page de notifications")

            Button(onClick = { navController.navigate(Screen.createAlarm.route) }) {
                Text(text = "Ajouter une notification")
            }
        }

    }
}











@Preview(showBackground = true, )
@Composable
fun NotificationPreview() {
    val navController = rememberNavController()
    MedreminderTheme {
        ViewNotifications(
            navController = navController
        )
    }
}



