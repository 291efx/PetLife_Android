package com.erick.petlife.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erick.petlife.ui.screens.auth.AuthViewModel
import com.erick.petlife.ui.screens.auth.LoginScreen
import com.erick.petlife.ui.screens.auth.RegisterScreen

@Composable
fun AppNavHost() {
    // ÚNICA instancia compartida del ViewModel de auth
    val authVm: AuthViewModel = hiltViewModel()
    val user by authVm.currentUser.collectAsState()

    val authNav = rememberNavController()

    if (user == null) {
        AuthGraph(nav = authNav, vm = authVm)
    } else {
        HomeNavHost(vm = authVm)
    }
}

@Composable
private fun AuthGraph(nav: NavHostController, vm: AuthViewModel) {
    NavHost(navController = nav, startDestination = Dest.Login.route) {
        composable(Dest.Login.route) {
            LoginScreen(
                onGoRegister = { nav.navigate(Dest.Register.route) },
                vm = vm
            )
        }
        composable(Dest.Register.route) {
            RegisterScreen(
                onBack = { nav.popBackStack() },
                vm = vm
            )
        }
    }
}
