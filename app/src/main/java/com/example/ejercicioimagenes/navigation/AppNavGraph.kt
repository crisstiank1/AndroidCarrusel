// navigation/AppNavGraph.kt
package com.example.ejercicioimagenes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import com.example.ejercicioimagenes.ui.screens.DetailsScreen
import com.example.ejercicioimagenes.ui.screens.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onOpenDetails = { id -> navController.navigate(Screen.Details.create(id)) })
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(Screen.Details.ARG_ID) { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt(Screen.Details.ARG_ID) ?: -1
            DetailsScreen(id = id, onBack = { navController.popBackStack() })
        }
    }
}
