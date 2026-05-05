package com.example.elitedev.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  DashboardScreen (navController: NavController){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                drawerContainerColor = Color.Black
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Coins",
                        modifier = Modifier.clickable{
                            navController.navigate("coins")
                        },
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Protocols",
                        modifier = Modifier.clickable{
                            navController.navigate("protocols")
                        },
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "trade",
                        modifier = Modifier.clickable{
                            navController.navigate("chart")
                        },
                        color = Color.Blue
                    )
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text(text = "Kraken")},

                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu , contentDescription = "Menu")
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black
                    )

                )
            }
        ) { padding ->
            Box(
                modifier = Modifier.padding(padding),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Coins",
                    modifier = Modifier.clickable{
                        navController.navigate("coins")
                    },
                    color = Color.Blue
                )
            }
        }
    }
}