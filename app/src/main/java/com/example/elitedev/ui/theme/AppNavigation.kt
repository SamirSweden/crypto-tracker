package com.example.elitedev.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elite.ui.LoginScreen
import com.example.elitedev.ui.theme.data.CoinsScreen
import com.example.elitedev.ui.theme.screen.HomeScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable ("login"){
            LoginScreen(username = "Samir")
        }
        composable("coins"){
            CoinsScreen()
        }
    }
}