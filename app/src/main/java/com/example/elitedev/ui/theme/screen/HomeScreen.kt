package com.example.elitedev.ui.theme.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun HomeScreen(
    username: String
) {
    Text(text = "Hello, $username")
}