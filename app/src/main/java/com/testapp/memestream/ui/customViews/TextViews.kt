package com.testapp.memestream.ui.customViews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.testapp.memestream.ui.theme.White
import com.testapp.memestream.ui.theme.regular
import com.testapp.memestream.ui.theme.textStyleAppName

@Composable
fun TextAppTitle(){
    Text(
        text = "MemeStream",
        style = textStyleAppName
    )
}
@Composable
fun TextRegular(
    modifier: Modifier = Modifier,
    text: String = "Empty",
    textStyle: TextStyle = regular(),
    textColor: Color = White
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle,
        color = textColor,
    )
}