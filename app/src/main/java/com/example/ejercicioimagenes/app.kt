// App.kt
package com.example.ejercicioimagenes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicioimagenes.navigation.AppNavGraph

@Composable
fun App() {
    val navController = rememberNavController()
    AppNavGraph(navController)
}
