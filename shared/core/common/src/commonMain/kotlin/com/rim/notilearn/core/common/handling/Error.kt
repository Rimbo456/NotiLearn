package com.rim.notilearn.core.common.handling

sealed interface Error {
    data object Network : Error
    data object Unauthorized : Error
    data object Server : Error
    data object Unknow : Error
}