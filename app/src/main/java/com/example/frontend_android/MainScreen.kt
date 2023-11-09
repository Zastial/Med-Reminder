package com.example.frontend_android

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.frontend_android.navigation.NavigationGraph
import com.example.frontend_android.navigation.RootScreen


// Ecran contenant la barre de navigation et le graphe de navigation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val bottomNavigationItem: List<RootScreen> = listOf(
        RootScreen.prescription,
        RootScreen.notification
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavigationItem.forEach { screen ->
                    val isSelected = isSelected(currentDestination, screen.route)
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = screen.icon),
                                contentDescription = null,
                                tint = if (isSelected) Color.Red else Color.White
                            )
                        },
                        label = {
                            Text(
                                stringResource(id = screen.ressourceID),
                                color = if (isSelected) Color.Red else Color.White
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

// retourne un boolean pour savoir si la destination appartient au graph courant
// si oui, il est sélectionné dans la barre de navigation
fun isSelected(currentDestination: NavDestination?, screenRoute: String): Boolean {
    return currentDestination?.hierarchy?.any { it.route === screenRoute } == true
}

@Composable
fun BottomNavBar(

) {
}

