package com.example.chat_with_gemini.presentation.navgraph

sealed class Route(
    val route: String
) {
    data object HomeScreen: Route("HomeScreen")
}