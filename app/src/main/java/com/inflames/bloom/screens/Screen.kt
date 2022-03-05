package com.inflames.bloom.screens

import androidx.compose.runtime.Immutable

sealed class Screen(val route: String) {

    @Immutable
    object Welcome : Screen("Welcome")

    @Immutable
    object Login : Screen("Login")

    object Home : Screen("Home")

    object Favorites : Screen("Favorites")

    object Profile : Screen("Profile")

    object Cart : Screen("Cart")


}
