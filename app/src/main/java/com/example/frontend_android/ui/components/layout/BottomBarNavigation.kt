package com.example.frontend_android.ui.components.layout

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.frontend_android.navigation.RootScreen

val bottomNavigationItem: List<RootScreen> = listOf(
    RootScreen.prescription,
    RootScreen.notification,
    RootScreen.medicine,
    RootScreen.user
)

@Composable
fun BottomBarNavigation(
    navController: NavController
) {

    NavigationBar(
        modifier = Modifier.height(70.dp),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavigationItem.forEach { screen ->
            val isSelected = isSelected(currentDestination, screen.route)
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.icon),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = screen.ressourceID),
                        color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurface ,
                        fontSize = 10.sp
                    )
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        // gestion du graph de navigation (pile de destinations à partir d'un noeud de départ)
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

fun isSelected(currentDestination: NavDestination?, screenRoute: String): Boolean {
    return currentDestination?.hierarchy?.any { it.route === screenRoute } == true
}
