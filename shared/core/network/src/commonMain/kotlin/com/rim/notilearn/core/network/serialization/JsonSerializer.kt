package com.rim.notilearn.core.network.serialization

import kotlinx.serialization.json.Json

object JsonSerializer {
    val default: Json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
        encodeDefaults = true
        coerceInputValues = true
    }

    val relaxed: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}