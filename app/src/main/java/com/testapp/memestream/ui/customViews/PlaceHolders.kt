package com.testapp.memestream.ui.customViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.testapp.memestream.ui.theme.Black
import com.testapp.memestream.ui.theme.shimmerBrush

@Composable
fun PostPlaceHolder() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(3) {
            PostItemPlaceHolder()
        }
    }
}

@Composable
fun PostItemPlaceHolder() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier
                .size(width = 125.dp, height = 50.dp)
                .padding(horizontal = 16.dp)
                .background(shimmerBrush()),
        ) {}
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 16.dp)
                .background(shimmerBrush()),
        ) {}
        Row(
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp)
                .padding(horizontal = 16.dp)
                .background(shimmerBrush()),
        ) {}
    }
}
