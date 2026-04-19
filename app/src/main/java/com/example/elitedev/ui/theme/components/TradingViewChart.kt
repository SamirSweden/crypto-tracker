package com.example.elitedev.ui.theme.components

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun TradingViewChart(
    symbol: String = "BINANCE:BTCUSDT"
){
    AndroidView(
        factory = {context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true

                setBackgroundColor(android.graphics.Color.BLACK)

                loadData(
                    """
                    <html>
                    <body>
                        <div id="tv_chart"></div>
                        <script src="https://s3.tradingview.com/tv.js"></script>
                        <script>
                          new TradingView.widget({
                            "width": "100%",
                            "height": 500,
                            "symbol": "$symbol",
                            "theme": "dark",
                            "container_id": "tv_chart"
                          });
                        </script>
                    </body>
                    </html>
                    """.trimIndent(),
                    "text/html",
                    "UTF-8"
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
            .height(400.dp)
    )
}