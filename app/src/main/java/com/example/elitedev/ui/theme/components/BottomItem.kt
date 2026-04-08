package com.example.elitedev.ui.theme.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun  BottomItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    var color = if(isSelected) Color.Yellow else Color.Gray

    Text(
        text = title,
        color = color,
        modifier = Modifier.clickable{ onClick() }
    )
}


