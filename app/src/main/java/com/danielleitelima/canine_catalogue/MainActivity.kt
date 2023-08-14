package com.danielleitelima.canine_catalogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.danielleitelima.canine_catalogue.presentation.favorite.FavoritesScreen
import com.danielleitelima.canine_catalogue.presentation.home.HomeScreen
import com.danielleitelima.canine_catalogue.presentation.photo_view.PhotoViewScreen
import com.danielleitelima.canine_catalogue.presentation.splash.SplashScreen
import com.danielleitelima.canine_catalogue.shared_ui.theme.CanineCatalogueTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CanineCatalogueTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = Route.SPLASH
                    ) {
                        composable(
                            route = Route.SPLASH,
                        ) {
                            SplashScreen{
                                navController.navigate(Route.HOME) {
                                    popUpTo(Route.SPLASH) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        }

                        composable(Route.HOME) {
                            Scaffold(
                                bottomBar = { BottomNavigation(navController) },
                                scaffoldState = scaffoldState
                            ) {
                                HomeScreen(
                                    modifier = Modifier.padding(it),
                                ) { itemId ->
                                    navController.navigateToPhotoView(itemId.toString())
                                }
                            }
                        }
                        composable(
                            route = Route.PHOTO_VIEW + "/{itemId}",
                            arguments = listOf(
                                navArgument("itemId") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            PhotoViewScreen()
                        }
                        composable(Route.FAVORITES) {
                            Scaffold(
                                bottomBar = { BottomNavigation(navController) },
                                scaffoldState = scaffoldState
                            ) {
                                FavoritesScreen(
                                    modifier = Modifier.padding(it)
                                ) { itemId ->
                                    navController.navigateToPhotoView(itemId)
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

private fun NavController.navigateToPhotoView(itemId: String) {
    navigate(
        Route.PHOTO_VIEW + "/$itemId"
//        Route.PHOTO_VIEW + "/test"
    )
}

@Composable
fun BottomNavigation(navController: NavController) {
    val bottomMenuItemsList = prepareBottomMenu()
    var currentRoute by remember { mutableStateOf(Route.HOME) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    navBackStackEntry?.let {
        currentRoute = it.destination.route ?: Route.HOME
    }

    BottomAppBar(
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiary,
        contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiary,
        cutoutShape = CircleShape
    ) {
        bottomMenuItemsList.forEach { menuItem ->
            BottomNavigationItem(
                selected = (currentRoute == menuItem.route),
                onClick = {
                    if (currentRoute != menuItem.route) {
                        navController.navigate(menuItem.route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = menuItem.icon,
                        contentDescription = menuItem.label
                    )
                }
            )
        }
    }
}

private fun prepareBottomMenu(): List<BottomMenuItem> {
    val bottomMenuItemsList = arrayListOf<BottomMenuItem>()

    bottomMenuItemsList.add(BottomMenuItem(label = "Home", icon = Icons.Filled.Home, route = Route.HOME))
    bottomMenuItemsList.add(BottomMenuItem(label = "Favorites", icon = Icons.Filled.Favorite, route = Route.FAVORITES))

    return bottomMenuItemsList
}

data class BottomMenuItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

