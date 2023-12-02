package com.example.frontend_android.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.frontend_android.navigation.NavigationGraph
import com.example.frontend_android.navigation.RootScreen
import com.example.frontend_android.ui.theme.Cyan500
import com.example.frontend_android.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar() {

    val navController = rememberNavController()
    val bottomNavigationItem: List<RootScreen> = listOf(
        RootScreen.prescription,
        RootScreen.notification,
        RootScreen.medicine,
        RootScreen.user
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavigationItem.forEach { screen ->
                    val isSelected = isSelected(currentDestination, screen.route)
                    val colorNavItem = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = screen.icon),
                                contentDescription = null,
                                tint = colorNavItem
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = screen.ressourceID),
                                color = colorNavItem,
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
    ) {
        Modifier.padding(it)
        NavigationGraph(navController)
    }
}

fun isSelected(currentDestination: NavDestination?, screenRoute: String): Boolean {
    return currentDestination?.hierarchy?.any { it.route === screenRoute } == true
}

@Preview
@Composable
fun navPreview() {
    NavigationBar()
}