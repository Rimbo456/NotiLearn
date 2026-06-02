package com.rim.notilearn.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary")
data class VocabularyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val ipa: String? = null,
    val partOfSpeechL: String? = null,
    val meaning: String,

    val defaultExampleEn: String? = null,
    val defaultExampleVn: String? = null,

    val aiExampleEn: String? = null,
    val aiExampleVn: String? = null,

    val intervalLevel: Int = 1,
    val nextReviewTime: Long,

    val status: String,
    val topic: String? = null
)