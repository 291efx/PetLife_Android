package com.erick.petlife.ui.nav

sealed class Dest(val route: String) {
    // Auth
    data object Login    : Dest("login")
    data object Register : Dest("register")

    // Tabs (Home)
    data object Agenda   : Dest("agenda")
    data object Recipes  : Dest("recipes")
    data object Posts    : Dest("posts")
    data object Profile  : Dest("profile")
}
