package com.erick.petlife.ui.nav

sealed class Dest(val route: String) {
    data object Login : Dest("login")
    data object Register : Dest("register")
    data object Home : Dest("home")
    // Tabs internas:
    data object Pets : Dest("pets")
    data object Recipes : Dest("recipes")
    data object Profile : Dest("profile")
    data object Posts : Dest("posts") // añadiste package post, lo incluimos
}
