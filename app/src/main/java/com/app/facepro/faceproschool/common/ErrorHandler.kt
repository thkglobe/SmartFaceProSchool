package com.app.facepro.faceproschool.common

import java.lang.Exception
import java.net.UnknownHostException

class ErrorHandler {
    infix fun checkError(exception: Exception) =
        when (exception) {
            is UnknownHostException -> "No connectivity, check your internet connection"
            else -> exception.message
        }
}
