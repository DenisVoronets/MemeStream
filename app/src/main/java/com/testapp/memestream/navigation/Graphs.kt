package com.testapp.memestream.navigation

object Graphs {
    // Also can define graphs for future use
    const val MAIN_GRAPH = "main_graph"
    const val LOG_IN_GRAPH = "log_in_graph"
    const val LOADING_GRAPH = "loading_graph"
}
sealed class Destination(val destination: String) {
    data object HomeScreenDestination : Destination("Home")
    data object CreatePostDestination : Destination("Create post")
    data object ProfileDestination : Destination("Profile")
}