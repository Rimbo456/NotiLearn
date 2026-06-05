package com.rim.notilearn.core.network.response

import com.rim.notilearn.core.network.dto.VocabularyAiDto
import kotlinx.serialization.Serializable

@Serializable
data class GeminiVocabularyResponse(
    val vocabularyList: List<VocabularyAiDto>
)