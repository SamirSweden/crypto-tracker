package com.example.elitedev.ui.theme.data


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.util.Locale

private val Background = Color(0xFF050505)
private val CardColor = Color(0xFF151515)
private val Green = Color(0xFF4ADE80)
private val Red = Color(0xFFFF6464)

@Composable
fun CoinsScreen() {
    var coins by remember {
        mutableStateOf<List<CoinDto>>(emptyList())
    }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    fun loadPrices() {
        scope.launch {
            loading = true
            error = null

            try {
                coins = CoinRetrofit.coinApi.get_prices()
            } catch (e: Exception) {
                error = e.localizedMessage ?: "Request failed"
            } finally {
                loading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadPrices()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Markets",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = "Crypto prices · USD",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            TextButton(onClick = { loadPrices() }) {
                Text("Refresh", color = Color.White)
            }
        }

        Spacer(Modifier.height(20.dp))

        when {
            loading && coins.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            }

            error != null && coins.isEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Couldn't load prices",
                        color = Color.White,
                        fontSize = 18.sp
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = error ?: "",
                        color = Color.Gray
                    )

                    Button(
                        onClick = { loadPrices() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Try again")
                    }
                }
            }

            coins.isEmpty() -> {
                Text(
                    text = "No coins available",
                    color = Color.White
                )
            }

            else -> {
                if (loading) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                }

                if (error != null) {
                    Text(
                        text = "Refresh failed. Showing previous prices.",
                        color = Red,
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.height(8.dp))
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(
                        items = coins,
                        key = { it.id }
                    ) { coin ->
                        CoinCard(coin)
                    }
                }
            }
        }
    }
}

@Composable
private fun CoinCard(coin: CoinDto) {
    val changeColor = when {
        coin.change24h > 0 -> Green
        coin.change24h < 0 -> Red
        else -> Color.Gray
    }

    val priceFormat = if (coin.priceUsd >= 1.0) {
        "%,.2f"
    } else {
        "%,.6f"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = coin.name,
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = "${coin.symbol.uppercase()}  ·  #${coin.rank}",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$" + String.format(
                        Locale.US,
                        priceFormat,
                        coin.priceUsd
                    ),
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = changeColor.copy(alpha = 0.12f)
                ) {
                    Text(
                        text = String.format(
                            Locale.US,
                            "%+.2f%%",
                            coin.change24h
                        ),
                        color = changeColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(
                            horizontal = 9.dp,
                            vertical = 5.dp
                        )
                    )
                }
            }
        }
    }
}