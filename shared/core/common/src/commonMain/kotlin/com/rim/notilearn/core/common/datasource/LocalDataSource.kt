package com.rim.notilearn.core.common.datasource

interface LocalDataSource {
    fun observe()

    suspend fun replaceAll()
}