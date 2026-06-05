package com.rim.notilearn.core.network.di

import com.rim.notilearn.core.network.api.VocabularyApi
import com.rim.notilearn.core.network.client.HttpClientFactory
import com.rim.notilearn.core.network.client.KtorHttpClientFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object NetworkModule {
    fun getModule(): Module = module {
        single<HttpClientFactory> { KtorHttpClientFactory() }

        single { createHttpClientEngine() }

        single<HttpClient> {
            val factory = get<HttpClientFactory>()
            val engine = get<HttpClientEngine>()
            factory.create(engine)
        }

        single { VocabularyApi(get()) }
    }
}