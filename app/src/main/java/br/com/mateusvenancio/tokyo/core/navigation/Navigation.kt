package br.com.mateusvenancio.tokyo.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.mateusvenancio.tokyo.ui.presenter.screens.HomeScreen
import br.com.mateusvenancio.tokyo.ui.presenter.screens.OperationsFormScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) { HomeScreen(navController) }
        composable(Screens.OperationsFormScreen.route) { OperationsFormScreen(navController) }
    }
}