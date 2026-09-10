package com.example.elitedev.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elitedev.ui.theme.screen.LoginScreen
import com.example.elitedev.ui.theme.screen.PanelScreen


@Composable
fun AppNavigation() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "login"
    ){


        composable("login") {

            LoginScreen(
                onLoginSuccess = { username ->

                    navController.navigate("panel/$username")
                }
            )
        }



        composable(
            route = "panel/{username}"
        ) { backStackEntry ->

            val username = backStackEntry
                .arguments
                ?.getString("username")
                ?: ""

            PanelScreen(
                username = username,
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("panel/{username}"){
                            inclusive=true
                        }

                        launchSingleTop=true
                    }
                }
            )
        }

    }

}