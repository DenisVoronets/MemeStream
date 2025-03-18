package com.testapp.memestream.ui.customViews

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun IconButtonWithText(
    text: String,
    icon: Painter,
    iconTint: Color = LocalContentColor.current,
    onIconClick: () -> Unit
) {
    Row {
        IconButton(onClick = onIconClick) {
            Icon(
                painter = icon,
                tint = iconTint,
                contentDescription = null
            )
        }
        TextRegular(text = text, modifier = Modifier.align(Alignment.CenterVertically))
    }
}