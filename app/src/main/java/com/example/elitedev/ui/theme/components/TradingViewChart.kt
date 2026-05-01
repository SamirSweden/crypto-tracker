package com.example.elitedev.ui.theme.components

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun TradingViewChart(
    symbol: String = "BINANCE:BTCUSDT",
    onLoaded: () -> Unit = {}
){
    AndroidView(
        factory = {context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                setBackgroundColor(android.graphics.Color.BLACK)

                webViewClient = object : android.webkit.WebViewClient(){
                    override fun onPageFinished(view: WebView? , url: String?){
                        onLoaded()
                    }
                }

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
        modifier = Modifier.fillMaxWidth().padding(top = 32.dp)
    )
}