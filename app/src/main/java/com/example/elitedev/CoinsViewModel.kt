package com.example.elitedev

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elitedev.data.Coin
import com.example.elitedev.data.RetrofitClient
import kotlinx.coroutines.launch

import androidx.compose.runtime.getValue // by
import androidx.compose.runtime.setValue // by
class CoinsViewModel: ViewModel() {

    var coins by mutableStateOf<List<Coin>>(emptyList())
    var isLoading by mutableStateOf(false)

    init {
        loadCoins()
    }

    private fun loadCoins(){
        viewModelScope.launch {
            isLoading = true
            try {
                coins = RetrofitClient.api.getCoins()
            }catch (err: Exception){

            }
            finally {
                isLoading = false
            }
        }
    }
}