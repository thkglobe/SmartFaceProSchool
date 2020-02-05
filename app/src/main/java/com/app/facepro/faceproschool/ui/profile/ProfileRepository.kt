package com.app.facepro.faceproschool.ui.profile

import com.app.facepro.faceproschool.api.ApiService
import com.app.facepro.faceproschool.ui.profile.model.ProfileRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProfileRepository(private val apiService: ApiService) {
    suspend fun updateProfileInfo(profileRequest: ProfileRequest?) = withContext(Dispatchers.IO) {
        apiService.getProfile(profileRequest)
    }

    suspend fun updateProfilePicture(body: MultipartBody.Part, userId: RequestBody) =
        withContext(Dispatchers.IO) {
            apiService.uploadProfilePhoto(userId, body)

        }

}
