package com.rim.notilearn.core.network.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine

interface HttpClientFactory {
    fun create(engine: HttpClientEngine): HttpClient
}

class KtorHttpClientFactory : HttpClientFactory {
    override fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine).apply {
            HttpClientConfig.configure(this)
        }
    }
}