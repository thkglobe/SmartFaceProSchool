package com.app.facepro.faceproschool.common


sealed class Result {
    object Progress : Result()
    data class Success<T>(val response:T) : Result()
    data class Failure(val exceptionMsg: String?) : Result()
    data class Error(val error: String) : Result()
}
