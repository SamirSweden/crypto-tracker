package com.example.elitedev.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.elitedev.KrakenProBanner


@Composable
fun ProtocolsScreen(navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp , vertical = 8.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.Black.copy(alpha = 0.4f))
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Kraken",
                            fontSize = 30.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace
                        )

                        Button(
                            onClick = {
                                navController.navigate("dashboard")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Text(
                                text = "Dashboard",
                                fontSize = 15.sp,
                            )
                        }
                    }
                }
            }

        item { BannerProtocol() }
        item { BannerPhoto() }
        item { ProtocolCardsGrid() }
        item{
            HeadingWhyCard()
            WhySection()
        }



    }
}

@Composable
fun BannerProtocol() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .padding(20.dp)
    ) {
        Column {

            Text(
                text = "— Protocols —",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Everything protocols need to launch and scale",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "From launch planning to liquidity, custody, staking, and user growth, Kraken 360 supports teams at every stage.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}



@Composable
fun BannerPhoto(){
    AsyncImage(
        model = "https://assets-cms.kraken.com/images/51n36hrp/facade/44f8f2be8b7e25f046e970ce56ae66399f3e67c6-2022x760.png?w=1536&fit=min",
        contentDescription = "protocols in Kraken",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 10.dp, bottom = 10.dp),
        onError = {error ->
            println("img error ${error.result.throwable}")
        },
        contentScale = ContentScale.Crop
    )
}


@Composable
fun ProtocolCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .heightIn(min = 140.dp)    // fixed height to all cards
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun ProtocolCardsGrid() {

    val items = listOf(
        "Token liquidity" to "Launch game plan for market maker liquidity.",
        "Custody" to "Secure custody with governance and compliance.",
        "Token distributions" to "Investor distributions with lockups.",
        "Treasury management" to "Secure and protected transactions."
    )

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items.chunked(2).forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { (title, desc) ->
                    ProtocolCard(
                        title = title,
                        description = desc,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        KrakenProBanner()
    }
}


@Composable
fun HeadingWhyCard(){
    Text(
        text = "Why protocols choose Kraken 360",
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp , vertical = 8.dp),
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 18.sp,
        textAlign = TextAlign.Start
    )
}

data class WhyItem(
    val id: Int,
    val title: String,
    val description: String,
    val icon: ImageVector
)

val whyListData = listOf(
    WhyItem(1,"Scale", "Built-in distribution with millions of users and institutional liquidity", icon = Icons.Default.TrendingUp),
    WhyItem(2,"Security and compliance", "Resilient operations and unmatched security with a global regulatory footprint.", icon = Icons.Default.Shield),
    WhyItem(3,"Dedicated 24/7/365 support", "Around-the-clock support complemented by dedicated relationship managers focused on institutional clients.", icon = Icons.Default.HeadsetMic),
    WhyItem(4,"Staking", "Transparent staking infrastructure designed for institutional participation and seamless reward collection.", icon = Icons.Default.Layers)
)

@Composable
fun WhySection(){
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        whyListData.forEach { item ->
            WhyCard(
                title = item.title,
                description = item.description,
                icon = item.icon
            )
        }
    }
}


@Composable
fun WhyCard(
    title: String,
    description: String,
    icon: ImageVector
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp , vertical = 8.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF111111),
            contentColor = Color.White
        ),
        border = BorderStroke(1.dp , Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp) // space between h1 and p
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF8C52FF),
                modifier = Modifier.size(32.dp)
            )

            Text(
                text = title,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Text(
                text = description,
                fontFamily = FontFamily.Default,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                color = Color.LightGray
            )

        }
    }
}








