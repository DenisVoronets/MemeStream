package com.testapp.memestream.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.testapp.memestream.ui.customViews.TextRegular
import com.testapp.memestream.ui.theme.Black

@Composable
fun CreatePostScreen(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextRegular(text = "Create post Screen")
        TextRegular(text = "Add a screen with a gallery of media files, request access to files, and save the added files to the user's profile (like a page with posts)")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreatePostScreenPreview() {
    ProfileScreen()
}