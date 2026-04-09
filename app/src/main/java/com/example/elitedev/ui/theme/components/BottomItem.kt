package com.example.elitedev.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun  BottomItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    var color = if(isSelected) Color.White else Color.Transparent


    Box(
        modifier = Modifier.background(
            color = color,
            shape = RoundedCornerShape(20.dp)
        )
            .clickable { onClick() }
            .padding(vertical = 12.dp , horizontal = 16.dp)
    ){
        Text(
            text = title,
            color = if(isSelected)  Color.Black else Color.White
        )
    }

}


