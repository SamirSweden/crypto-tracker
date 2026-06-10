package com.example.elitedev.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.annotation.OptIn

@OptIn(UnstableApi::class)
@Composable
fun BannerVideo (
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier

){
    val context = LocalContext.current
    val videoUrl = "https://www.okx.com/cdn/assets/files/2411/58251908C91AAF59.mp4"

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply{
            val mediaItem = MediaItem.fromUri(videoUrl)
            setMediaItem(mediaItem)
            repeatMode = Player.REPEAT_MODE_ALL
            playWhenReady = true
            volume = 0f
            prepare()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(
            factory = {ctx ->
                PlayerView(ctx).apply{
                    player = exoPlayer
                    useController = false
                    resizeMode = androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            },
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
        )

       Button(
           onClick = onPlayClick,
           modifier = Modifier.
                    fillMaxWidth(0.7f).padding(24.dp)
       ) {
           Text(
               text = "crypto game"
           )
       }
    }
}

