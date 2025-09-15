package com.erick.petlife.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.erick.petlife.ui.screens.auth.AuthViewModel
import com.erick.petlife.ui.screens.agenda.AgendaScreen
import com.erick.petlife.ui.screens.posts.PostsScreen
import com.erick.petlife.ui.screens.profile.ProfileScreen
import com.erick.petlife.ui.screens.recipes.RecipesScreen

/** Home con BottomBar: Agenda / Recetas / Comunidad / Perfil */
@Composable
fun HomeNavHost(vm: AuthViewModel = hiltViewModel()) {
    val inner = rememberNavController()

    data class Tab(val dest: Dest, val label: String, val icon: ImageVector)
    val tabs = remember {
        listOf(
            Tab(Dest.Agenda,  "Agenda",    Icons.Outlined.CalendarToday),
            Tab(Dest.Recipes, "Recetas",   Icons.Outlined.List),
            Tab(Dest.Posts,   "Comunidad", Icons.Outlined.Public),
            Tab(Dest.Profile, "Perfil",    Icons.Outlined.Person),
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                val current = inner.currentBackStackEntryAsState().value?.destination?.route
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = current == tab.dest.route,
                        onClick = {
                            if (current != tab.dest.route) {
                                inner.navigate(tab.dest.route) {
                                    launchSingleTop = true
                                    popUpTo(inner.graph.startDestinationId) { saveState = true }
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) },
                        alwaysShowLabel = true
                    )
                }
            }
        }
    ) { padding ->
        InnerHomeNav(inner, padding, vm)
    }
}

@Composable
private fun InnerHomeNav(
    nav: NavHostController,
    padding: PaddingValues,
    vm: AuthViewModel
) {
    NavHost(
        navController = nav,
        startDestination = Dest.Agenda.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(Dest.Agenda.route)  { AgendaScreen(padding) }
        composable(Dest.Recipes.route) { RecipesScreen(padding) }
        composable(Dest.Posts.route)   { PostsScreen(padding) }
        composable(Dest.Profile.route) { ProfileScreen(padding, vm) }
    }
}
