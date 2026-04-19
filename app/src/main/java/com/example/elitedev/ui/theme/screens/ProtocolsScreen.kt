package com.example.elitedev.ui.theme.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Kraken", color = Color.White)
                Button(onClick = {}) {
                    Text("Panel")
                }
            }
        }

        item { BannerProtocol() }
        item { BannerPhoto() }
        item { ProtocolCardsGrid() }

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










