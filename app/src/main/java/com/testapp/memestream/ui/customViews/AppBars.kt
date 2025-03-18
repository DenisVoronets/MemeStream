package com.testapp.memestream.ui.customViews

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.testapp.memestream.R
import com.testapp.memestream.data.Constants.BottomNavItems
import com.testapp.memestream.ui.theme.Black
import com.testapp.memestream.ui.theme.White
import com.testapp.memestream.ui.theme.navBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMain(
    onNotificationClick: () -> Unit
) {
    TopAppBar(
        title = {
            TextAppTitle()
        },
        actions = {
            IconButton(
                onClick = onNotificationClick,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.like_icon_outlined),
                    tint = White,
                    contentDescription = "Notification icon"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Black
        )
    )
}

@Composable
fun BottomBarMain(
    navHostController: NavHostController
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        containerColor = Black
    ) {
        BottomNavItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    if (currentRoute != navItem.route) {
                        navHostController.navigate(navItem.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navHostController.graph.startDestinationId) { saveState = true }
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = if (currentRoute == navItem.route) navItem.iconFilled else navItem.iconOutlined),
                        contentDescription = navItem.label
                    )
                },
                label = {
                    TextRegular(text = navItem.label)
                },
                alwaysShowLabel = false,
                colors = navBarColors()
            )
        }
    }
}
