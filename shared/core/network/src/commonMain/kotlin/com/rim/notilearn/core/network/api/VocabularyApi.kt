package com.rim.notilearn.core.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

//interface VocabularyApi {
//    suspend fun getVocabulary(id: Int): VocabularyDto
//    suspend fun searchVocabulary(keyword: String): List<VocabularyDto>
//}
//
//class KtorVocabularyApi : VocabularyApi, KoinComponent {
//    private val httpClient: HttpClient by inject()
//
//    override suspend fun getVocabulary(id: Int): VocabularyDto {
//        return httpClient.get("https://api.example.com/vocabulary/$id").body()
//    }
//
//    override suspend fun searchVocabulary(keyword: String): List<VocabularyDto> {
//        return httpClient.get("https://api.example.com/vocabulary/search?q=$keyword").body()
//    }
//}