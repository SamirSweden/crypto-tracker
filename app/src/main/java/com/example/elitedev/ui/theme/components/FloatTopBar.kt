package com.example.elitedev.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FloatTopBar (
    currentTab: String,
    onTabChange: (String) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Row(
            modifier = Modifier.fillMaxWidth(.09f) // делает "плавающий" эффект
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // tabs
        }
    }
}