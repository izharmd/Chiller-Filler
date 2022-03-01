package com.bws.chillerfiller.util

sealed class Resources2<T>(
    val data: T? = null,
    val loadingMessage: String? = null,
    val errorMessage: String? = null,
    val noInternetMessage: String? = null
) {

    class Success<T>(data: T? = null) : Resources2<T>(data = data)
    class Loading<T>(loadingMessage: String) : Resources2<T>(loadingMessage = loadingMessage)
    class Error<T>(errorMessage: String): Resources2<T>(errorMessage = errorMessage)
    class NoInternet<T>(noInternetMessage: String):Resources2<T>(noInternetMessage = noInternetMessage)
}
