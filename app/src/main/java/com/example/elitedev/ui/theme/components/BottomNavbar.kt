package com.example.elitedev.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BottomNavbar(
    select: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier.
            fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 12.dp , vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceAround // вертикально как в css space-around
    ) {
        BottomItem("Home",select == "home"){
            onSelect("home")
        }
        BottomItem("Coins" , select == "coins") {
            onSelect("coins")
        }
    }
}


