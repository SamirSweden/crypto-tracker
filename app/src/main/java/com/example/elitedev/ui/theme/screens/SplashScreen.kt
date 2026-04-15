package com.example.elitedev.ui.theme.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue

fun Modifier.animatePulse(): Modifier = composed{
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween (1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),

        label = "alpha"
    )

    this.graphicsLayer {
        this.alpha = alpha
    }
}



@Composable
fun SplashScreen (onNavigate: () -> Unit){


    LaunchedEffect(
        Unit
    ) {
        delay(4000)
        onNavigate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Kraken",
                color = Color.White,
                fontSize = 70.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Invest in Kraken",
                color = Color.Yellow,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraLight
            )

            Text(
                text = "owner Ryan",
                color = Color.Gray,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.animatePulse()
            )
        }
    }

}