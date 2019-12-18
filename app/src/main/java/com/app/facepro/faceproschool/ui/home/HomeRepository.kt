package com.app.facepro.faceproschool.ui.home

import com.app.facepro.faceproschool.api.ApiService
import com.app.facepro.faceproschool.ui.home.model.HomeRequest
import com.app.facepro.faceproschool.ui.home.model.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val apiService: ApiService) {
    suspend fun fetchHomeData(homeRequest: HomeRequest): HomeResponse {
        return withContext(Dispatchers.IO) {
            apiService.fetchHomeData(homeRequest)
        }
    }
}
