package com.moodreel.core.common.error

sealed class AppException(message: String? = null, cause: Throwable? = null) :
    Exception(message, cause) {

    class NoInternet(cause: Throwable? = null) :
        AppException("No internet connection", cause)

    class Server(val code: Int, message: String? = null, cause: Throwable? = null) :
        AppException("Server error: $code${message?.let { " — $it" }.orEmpty()}", cause)

    class Parsing(cause: Throwable? = null) :
        AppException("Failed to parse response", cause)

    class Unknown(message: String? = null, cause: Throwable? = null) :
        AppException(message ?: "Unknown error", cause)
}
