package com.example.elitedev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.elitedev.ui.theme.EliteDevTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.elitedev.ui.theme.components.BottomNavbar
import com.example.elitedev.ui.theme.screens.CoinsScreen
import com.example.elitedev.ui.theme.screens.ProtocolsScreen
import kotlinx.coroutines.selects.select

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EliteDevTheme(darkTheme = true) {

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {

                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable("main"){
                            MainScreen(navController)
                        }

                        composable("coins"){
                            CoinsScreen()
                        }
                        composable("protocols") {
                            ProtocolsScreen()
                        }
                    }


                }
            }
        }
    }
}



@Composable
fun MainScreen(navController: NavHostController) {
    var tab by remember { mutableStateOf("home") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,

        bottomBar = {
            BottomNavbar(
                select = tab,
                onSelect = {tab = it}
            )
        }

        // before innerPadding import bottomNavbar component
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            when (tab) {
                "home" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            KrakenBannerPhoto()
                            Banner(text = "Beyond crypto. Trade global assets with USDT.")
                            AboutKraken(text = "Kraken is crypto top-notch platform")
                            Spacer(modifier = Modifier.padding(16.dp))
                            BlackFullWidthButton(
                                text = "Get Started",
                                onClick = {
                                    tab = "coins"
                                }
                            )
                            KrakenBanner()
                        }


                    }
                }

                "coins" -> {
                    CoinsScreen()
                }

                "protocols" -> {
                    ProtocolsScreen()
                }
            }


        }
    }
}





@Composable
fun Banner(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text ,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-0.5).sp,
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun HelloElite(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to  $name!",
        modifier = modifier,
        color = Color.White,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EliteDevTheme {
        HelloElite("Kraken crypto app")
    }
}


@Composable
fun KrakenBanner() {
    AsyncImage(
        model = "https://assets-cms.kraken.com/images/51n36hrp/facade/945a2d697164d783a255908648607db9a7ceb951-4942x3200.png?w=1536&fit=min",
        contentDescription = "Kraken banner photo",
        modifier = Modifier.fillMaxWidth()
            .height(500.dp)
            .padding(top = 10.dp , bottom = 10.dp),
        onError = {error ->
            println("img error ${error.result.throwable}")
        },
        contentScale = ContentScale.Crop
        )
}
@Preview(showBackground = true)
@Composable
fun KrakenBannerPhoto() {
    AsyncImage(
        model = "https://www.unlock-bc.com/_next/image?url=https%3A%2F%2Fzougbjkrpntpnwbggivh.supabase.co%2Fstorage%2Fv1%2Fobject%2Fpublic%2Farticles%2F2025-migration%2F5c19114a-3a73-4949-93e5-714ca6f616d9-1768434717231-mbvd87.jpg&w=1920&q=75",
        contentDescription = "Kraken logo",
        modifier = Modifier.fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 8.dp),
        onError = {error ->
            println("img error ${error.result.throwable}")
        },
        contentScale = ContentScale.Crop
    )
}


@Composable
fun AboutKraken(
    text: String
){
    Text(
        text = text ,
        color = Color.White,
        fontFamily = FontFamily.Monospace,
        modifier = Modifier.padding(top = 16.dp) // padding-top:16px;
    )
}

@Composable
fun BlackFullWidthButton(
    text: String,
    onClick: () -> Unit
) {

    val haptic = LocalHapticFeedback.current
    Button(
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            fontFamily = FontFamily.Monospace
        )
    }
}


