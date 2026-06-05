package com.rim.notilearn.core.network.api

import com.rim.notilearn.core.network.BuildKonfig
import com.rim.notilearn.core.network.response.GeminiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.io.IOException

class VocabularyApi(
    private val httpClient: HttpClient
) {
    suspend fun generateVocabularyContent(rawPrompt: String): String {
        try {
            val requestBody = mapOf(
                "content" to listOf(
                    mapOf(
                        "parts" to listOf(
                            mapOf("text" to rawPrompt)
                        )
                    )
                ),
                "generationConfig" to mapOf(
                    "responseMimeType" to "application/json"
                )
            )

            val response: GeminiResponse = httpClient.post {
                url("https://generativelanguage.googleapis.com")
                url.path("v1beta", "models", "gemini-3.5-flash:generateContent")
                url.parameters.append("key", BuildKonfig.GEMINI_API_KEY)
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }.body()

            val text = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
                ?: ""
            return text
        } catch (e: IOException) {
            throw e
        }
    }
}