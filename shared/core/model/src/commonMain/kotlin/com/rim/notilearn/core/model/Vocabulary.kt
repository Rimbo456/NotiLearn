package com.rim.notilearn.core.model

import com.rim.notilearn.core.model.enum.VocabularyStatus
import kotlinx.serialization.Serializable

@Serializable
data class Vocabulary(
    val id: String,
    val word: String,
    val ipa: String? = null,
    val partOfSpeech: String? = null,
    val meaning: String,

    val defaultExampleEn: String? = null,
    val defaultExampleVn: String? = null,

    val aiExampleEn: String? = null,
    val aiExampleVn: String? = null,

    val intervalLevel: Int = 1,
    val nextReviewTime: Long,

    val status: VocabularyStatus = VocabularyStatus.LEARNING,
    val topic: String? = null
)