package com.example.chat_with_gemini.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chat_with_gemini.presentation.home.HomeScreen


@Composable
fun NavGraph(
    destination: String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = destination
    ) {
        composable(
            route = Route.HomeScreen.route
        ) {
            HomeScreen()
        }

    }
}