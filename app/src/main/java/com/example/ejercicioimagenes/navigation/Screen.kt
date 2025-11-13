package com.example.ejercicioimagenes.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Details : Screen("details/{id}") {
        const val ARG_ID = "id"
        fun create(id: Int) = "details/$id"
    }
}