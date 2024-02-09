package br.com.mateusvenancio.tokyo.core.navigation

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object OperationsFormScreen : Screens("operations_form")
}