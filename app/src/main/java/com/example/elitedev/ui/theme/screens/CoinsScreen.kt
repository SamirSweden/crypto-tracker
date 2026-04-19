package com.example.elitedev.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.elitedev.ui.theme.components.CoinItem
import androidx.compose.foundation.lazy.items
import com.example.elitedev.CoinsViewModel

@Composable
fun CoinsScreen(viewModel: CoinsViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        if(viewModel.isLoading){
            CircularProgressIndicator(Modifier.align(Alignment.Center), color = Color.White)
        } else {
            LazyColumn {
                items(viewModel.coins){coins ->
                    CoinItem(coins)
                }
            }
        }
    }
}