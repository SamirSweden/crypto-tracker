package com.example.elite.ui




import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elitedev.ui.theme.data.CoinsScreen

private val Black = Color(0xFF050505)
private val White = Color(0xFFFFFFFF)
private val Muted = Color(0xFF999999)

@Composable
fun LoginScreen(username: String) {
    var selectedTab by remember { mutableStateOf("Home") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Black,
        bottomBar = {
            BottomTabs(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Black)
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            when (selectedTab) {
                "Home" -> {
                    GreetingBanner(username = username)

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = "Overview",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Your dashboard is ready.",
                        fontSize = 14.sp,
                        color = Muted
                    )
                }

                "Coins" -> {
                    CoinsScreen()
                }
            }
        }
    }
}

@Composable
private fun GreetingBanner(username: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF333333),
                        Color(0xFF151515),
                        Color(0xFF080808)
                    )
                )
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(28.dp)
            )
    ) {
        // Мягкое размытое светлое пятно
        Box(
            modifier = Modifier
                .size(140.dp)
                .offset(x = 210.dp, y = (-35).dp)
                .blur(45.dp)
                .background(
                    Color.White.copy(alpha = 0.25f),
                    CircleShape
                )
        )

        // Тёмное пятно для глубины
        Box(
            modifier = Modifier
                .size(120.dp)
                .offset(x = 150.dp, y = 100.dp)
                .blur(40.dp)
                .background(
                    Color.Gray.copy(alpha = 0.25f),
                    CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ELITE  /  PERSONAL",
                fontSize = 11.sp,
                letterSpacing = 2.sp,
                color = Color.White.copy(alpha = 0.65f)
            )

            Column {
                Text(
                    text = "👋 Hello, ${username.trim()}",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Welcome back to your panel.",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.65f)
                )
            }
        }
    }
}

@Composable
private fun BottomTabs(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 12.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFF171717))
            .border(
                1.dp,
                Color.White.copy(alpha = 0.10f),
                RoundedCornerShape(28.dp)
            )
            .padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        TabItem(
            title = "Home",
            selected = selectedTab == "Home",
            icon = { tint ->
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = tint
                )
            },
            modifier = Modifier.weight(1f),
            onClick = { onTabSelected("Home") }
        )

        TabItem(
            title = "Coins",
            selected = selectedTab == "Coins",
            icon = { tint ->
                Icon(
                    imageVector = Icons.Default.CurrencyBitcoin,
                    contentDescription = "Coins",
                    tint = tint
                )
            },
            modifier = Modifier.weight(1f),
            onClick = { onTabSelected("Coins") }
        )
    }
}

@Composable
private fun TabItem(
    title: String,
    selected: Boolean,
    icon: @Composable (Color) -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val contentColor = if (selected) Black else Color(0xFFAAAAAA)

    Row(
        modifier = modifier
            .height(52.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(
                if (selected) White else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon(contentColor)

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            color = contentColor,
            fontSize = 14.sp,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Medium
            }
        )
    }
}
