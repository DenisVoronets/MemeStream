package com.testapp.memestream.data

import com.testapp.memestream.R
import com.testapp.memestream.navigation.Destination.HomeScreenDestination
import com.testapp.memestream.navigation.Destination.CreatePostDestination
import com.testapp.memestream.navigation.Destination.ProfileDestination


data class BottomNavItem(
    val label: String,
    val iconOutlined: Int,
    val iconFilled: Int,
    val route:String,
)
object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            iconOutlined = R.drawable.home_icon_outlined,
            iconFilled = R.drawable.home_icon_filled,
            route = HomeScreenDestination.destination
        ),
        BottomNavItem(
            label = "Create Post",
            iconOutlined = R.drawable.create_icon_outlined,
            iconFilled = R.drawable.create_icon_filled,
            route = CreatePostDestination.destination
        ),
        BottomNavItem(
            label = "Profile",
            iconOutlined = R.drawable.profile_icon_outlined,
            iconFilled = R.drawable.profile_icon_filled,
            route = ProfileDestination.destination
        )
    )
}
