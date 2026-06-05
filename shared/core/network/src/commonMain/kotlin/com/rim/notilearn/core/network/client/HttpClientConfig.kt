package com.rim.notilearn.core.network.client

import com.rim.notilearn.core.network.serialization.JsonSerializer
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

object HttpClientConfig {
    fun configure(client: HttpClient) {
        client.config {
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("[HttpClient] $message")
                    }
                }
            }

            install(ContentNegotiation) {
                json(
                    JsonSerializer.default
                )
            }
        }
    }
}