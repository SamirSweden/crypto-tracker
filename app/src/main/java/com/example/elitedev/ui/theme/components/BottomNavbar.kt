package com.example.elitedev.ui.theme.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun BottomNavbar(
    select: String,
    onSelect: (String) -> Unit
){


    NavigationBar (
        modifier = Modifier.fillMaxWidth()
            .background(Color.White.copy(alpha = 15f)),
        containerColor = Color.Black
    ) {
        NavigationBarItem(
            selected = select == "home",
            onClick = { onSelect("home") },
            icon = { Icon(Icons.Default.Home , contentDescription = "Home") },
            label= { Text("Home") }
        )

        NavigationBarItem(
            selected = select == "coins",
            onClick = { onSelect("coins") },
            icon = {Icon(Icons.Default.Star, contentDescription = "Coins")},
            label = { Text("Coins") }
        )

        NavigationBarItem(
            selected = select == "protocols",
            onClick = { onSelect("protocols")},
            icon = {Icon(Icons.Default.Settings, contentDescription = "Protocols")},
            label = {Text("Protocols")}
        )

        NavigationBarItem(
            selected = select == "login",
            onClick = {onSelect("login")},
            icon = {Icon(Icons.Default.Person , contentDescription = "login")},
            label = {Text("Login")}
        )
    }
}



