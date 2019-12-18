package com.app.facepro.faceproschool.ui.login

import com.app.facepro.faceproschool.api.ApiService
import com.app.facepro.faceproschool.ui.login.model.LoginRequest
import com.app.facepro.faceproschool.ui.login.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val apiService: ApiService) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return withContext(Dispatchers.IO) {
            apiService.login(loginRequest)
        }
    }
}
