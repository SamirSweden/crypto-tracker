package com.example.elitedev.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import com.example.elitedev.ui.theme.components.BottomMenu
import com.example.elitedev.ui.theme.components.TradingViewChart
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun ChartScreen (){
    var isLoading by remember { mutableStateOf(true) }


    Scaffold(
        bottomBar = {BottomMenu()}
    ) { innerPadding ->
        Column(

        ) {
            Box(
                modifier = Modifier.
                padding(innerPadding).
                height(300.dp)
            ){
                TradingViewChart(
                    symbol = "BINANCE:BTCUSDT",
                    onLoaded = {isLoading = false}
                )

                if(isLoading) {
                    Box(
                        modifier = Modifier.matchParentSize()
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer()
                            )
                    )
                }
            }
        }
    }
}

