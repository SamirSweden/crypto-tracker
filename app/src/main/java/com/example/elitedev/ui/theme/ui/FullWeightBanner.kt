package com.example.elitedev.ui.theme.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.elitedev.R


@Composable
fun FullWeightBanner() {
    AsyncImage(
        model = "https://cdn.dribbble.com/userupload/43533734/file/original-e26bd22e742146ba08f6fd7a3c427a77.jpg",
        contentDescription = "Crypto",
        modifier = Modifier.fillMaxWidth().height(180.dp),
        contentScale = ContentScale.Crop
    )
}