package com.inflames.bloom

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.inflames.bloom.screens.Screen
import com.inflames.bloom.screens.WelcomeScreen
import com.inflames.bloom.screens.bottomnav.BottomNavModel
import com.inflames.bloom.screens.cart.CartScreen
import com.inflames.bloom.screens.favorite.FavoriteScreen
import com.inflames.bloom.screens.home.HomeScreen
import com.inflames.bloom.screens.loginscreen.LoginScreen
import com.inflames.bloom.screens.profile.ProfileScreen

@Composable
fun BloomApp() {

    val bottomNavItems = listOf(
        BottomNavModel(Icons.Filled.Home, R.string.bottom_nav_home, Screen.Home),
        BottomNavModel(
            Icons.Filled.FavoriteBorder,
            R.string.bottom_nav_favorites,
            Screen.Favorites
        ),
        BottomNavModel(Icons.Filled.AccountCircle, R.string.bottom_nav_profile, Screen.Profile),
        BottomNavModel(Icons.Filled.ShoppingCart, R.string.bottom_nav_cart, Screen.Cart),
    )

    ProvideWindowInsets {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            modifier = Modifier.navigationBarsPadding(),
            bottomBar = {
                bottomNavItems.forEach {
                    if (currentDestination?.route == it.screen.route) {
                        BottomNavigation(backgroundColor = MaterialTheme.colors.primary) {
                            bottomNavItems.forEach { bottomNavItem ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            bottomNavItem.icon,
                                            contentDescription = stringResource(id = bottomNavItem.labelResourceId),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    },
                                    label = { Text(stringResource(bottomNavItem.labelResourceId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == bottomNavItem.screen.route } == true,
                                    onClick = {
                                        navController.navigate(bottomNavItem.screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items

//                                            popUpTo(navController.graph.findStartDestination().id) {
//                                                saveState = true
//                                            }
                                            popUpTo(Screen.Home.route)


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
                }

            }
        ) {

            NavHost(
                navController = navController, startDestination = Screen.Welcome.route
            ) {

                composable(Screen.Welcome.route) { WelcomeScreen(navController) }

                composable(Screen.Login.route) { LoginScreen(navController) }

                composable(Screen.Home.route) { HomeScreen(navController = navController) }

                composable(Screen.Favorites.route) { FavoriteScreen(navController = navController) }

                composable(Screen.Profile.route) { ProfileScreen(navController = navController) }

                composable(Screen.Cart.route) { CartScreen(navController = navController) }
            }
        }
    }
}



