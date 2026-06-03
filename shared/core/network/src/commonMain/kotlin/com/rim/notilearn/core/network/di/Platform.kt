package com.rim.notilearn.core.network.di

import io.ktor.client.engine.HttpClientEngine

expect fun createHttpClientEngine() : HttpClientEngine