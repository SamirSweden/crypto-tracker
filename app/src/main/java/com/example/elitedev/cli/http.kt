package com.example.elitedev.cli

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.*

val client = OkHttpClient()

suspend fun  checkUrl(url: String): Pair<String , Int> = withContext(Dispatchers.IO) {
    try {
        val req = Request.Builder()
            .url(url)
            .build()

        client.newCall(req).execute().use { response ->
            url to response.code
        }
    } catch (err: Exception) {
        url to -1   // timeout ,errors
    }
}

fun http() = runBlocking {
    val hosts = listOf(
        "https://www.youtube.com",
        "https://chatgpt.com/",
        "https://golangify.com/",
        "https://www.typescriptlang.org/"
    )

    val results = hosts.map{ url ->
        async {
            checkUrl(url)
        }
    }.awaitAll()

    results.forEach { (url , code) ->
        when(code){
            200     -> println("$url -> Ok 200")
            500     -> println("$url -> error 500")
            -1      -> println("$url - network error")
            else    -> println("$url -> http code is - $code")
        }
    }
}