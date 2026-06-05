package com.rim.notilearn.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class VocabularyAiDto(
    val word: String,
    val ipa: String,
    val partOfSpeech: String,
    val meaning: String,

    val defaultExampleEn: String,
    val defaultExampleVi: String,

    val aiExampleEn: String,
    val aiExampleVi: String,

    val topic: String
)