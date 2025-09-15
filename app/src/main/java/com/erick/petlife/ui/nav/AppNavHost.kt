package com.erick.petlife.ui.nav

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.NavHostController
import com.erick.petlife.ui.screens.auth.LoginScreen
import com.erick.petlife.ui.screens.auth.RegisterScreen
import com.erick.petlife.ui.screens.pets.PetsScreen
import com.erick.petlife.ui.screens.recipes.RecipesScreen
import com.erick.petlife.ui.screens.profile.ProfileScreen
import com.erick.petlife.ui.screens.posts.PostsScreen

@Composable
fun AppNavHost() {
    var loggedIn by remember { mutableStateOf(false) } // temporal; luego viene Firebase
    val nav = rememberNavController()

    if (!loggedIn) {
        AuthGraph(nav) { loggedIn = true }
    } else {
        HomeGraph()
    }
}

@Composable
private fun AuthGraph(nav: NavHostController, onLoggedIn: () -> Unit) {
    NavHost(navController = nav, startDestination = Dest.Login.route) {
        composable(Dest.Login.route) { LoginScreen(
            onGoRegister = { nav.navigate(Dest.Register.route) },
            onLoginSuccess = { onLoggedIn() }
        ) }
        composable(Dest.Register.route) { RegisterScreen(
            onBack = { nav.popBackStack() },
            onRegistered = { onLoggedIn() }
        ) }
    }
}

@Composable
private fun HomeGraph() {
    val inner = rememberNavController()
    val items = listOf(Dest.Pets, Dest.Recipes, Dest.Posts, Dest.Profile)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val current = inner.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        selected = current == item.route,
                        onClick = {
                            if (current != item.route) {
                                inner.navigate(item.route) { launchSingleTop = true }
                            }
                        },
                        icon = { /* iconos luego */ },
                        label = { Text(item.route.replaceFirstChar { it.uppercase() }) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(inner, startDestination = Dest.Pets.route, modifier = Modifier) {
            composable(Dest.Pets.route) { PetsScreen(padding) }
            composable(Dest.Recipes.route) { RecipesScreen(padding) }
            composable(Dest.Posts.route) { PostsScreen(padding) }
            composable(Dest.Profile.route) { ProfileScreen(padding) }
        }
    }
}
