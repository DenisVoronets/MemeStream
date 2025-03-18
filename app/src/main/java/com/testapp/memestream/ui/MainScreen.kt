package com.testapp.memestream.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.testapp.memestream.MainActivity
import com.testapp.memestream.models.HomeScreenViewModel
import com.testapp.memestream.navigation.Destination.HomeScreenDestination
import com.testapp.memestream.navigation.Destination.CreatePostDestination
import com.testapp.memestream.navigation.Destination.ProfileDestination
import com.testapp.memestream.ui.customViews.BottomBarMain
import com.testapp.memestream.ui.customViews.TopBarMain
import com.testapp.memestream.ui.theme.Black
import com.testapp.memestream.ui.theme.MemeStreamTheme
import com.testapp.memestream.ui.theme.slideIn
import com.testapp.memestream.ui.theme.slideOut
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navHostController: NavHostController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        topBar = {
            TopBarMain {
                //Todo add list of notifications
            }
        },
        bottomBar = {
            BottomBarMain(navHostController = navHostController)
        }
    ) { paddingValues ->
        MainScreenContent(
            paddingValues = paddingValues,
            navHostController = navHostController
        )
    }
}

@Composable
fun MainScreenContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    homeViewModel: HomeScreenViewModel= koinViewModel<HomeScreenViewModel>()
) {
    NavHost(
        navController = navHostController,
        startDestination = HomeScreenDestination.destination,
        modifier = Modifier.padding(paddingValues = paddingValues),
        enterTransition = { slideIn },
        exitTransition = { slideOut },
        builder = {
            composable(HomeScreenDestination.destination) {
                HomeScreen(homeViewModel)
            }
            composable(CreatePostDestination.destination) {
                CreatePostScreen()
            }
            composable(ProfileDestination.destination) {
                ProfileScreen()
            }
        })
}
