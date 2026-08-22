package com.example.elitedev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.elitedev.ui.theme.EliteDevTheme

import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elitedev.ui.theme.ui.Header


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            EliteDevTheme{

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color.Black,
                                    Color(0xFF180000),
                                    Color(0xFF5C0000)
                                )
                            )
                        )
                ){
                    AppNavigation()
                    MainScreen (onLoginClick = {
                        println("login clicked")
                    })
                }

            }
        }
    }
}


@Composable
fun MainScreen(
    onLoginClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            onLoginClick = onLoginClick
        )
    }
}




@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main"){
            MainScreen (
                onLoginClick = {
                    navController.navigate("login")
                }
            )
        }

        composable("login"){
            //
        }
    }
}










