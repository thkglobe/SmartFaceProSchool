package com.app.facepro.faceproschool.api

import com.app.facepro.faceproschool.firebase.model.FirebaseTokenResponse
import com.app.facepro.faceproschool.firebase.model.FirebaseTokenUpdate
import com.app.facepro.faceproschool.ui.home.model.HomeRequest
import com.app.facepro.faceproschool.ui.home.model.HomeResponse
import com.app.facepro.faceproschool.ui.login.model.LoginRequest
import com.app.facepro.faceproschool.ui.login.model.LoginResponse
import com.app.facepro.faceproschool.ui.notifications.model.NotificationRequest
import com.app.facepro.faceproschool.ui.notifications.model.NotificationResponse
import com.app.facepro.faceproschool.ui.otp.model.SendOtpRequest
import com.app.facepro.faceproschool.ui.otp.model.SendOtpResponse
import com.app.facepro.faceproschool.ui.profile.model.ProfilePictureResponse
import com.app.facepro.faceproschool.ui.profile.model.ProfileRequest
import com.app.facepro.faceproschool.ui.profile.model.ProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("LoginService")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("TokenService")
    suspend fun tokenUpdate(@Body tokenUpdate: FirebaseTokenUpdate): FirebaseTokenResponse

    @POST("NotificationList")
    suspend fun getNotifications(@Body notificationRequest: NotificationRequest): NotificationResponse

    @POST("ProfileService")
    suspend fun getProfile(@Body profileRequest: ProfileRequest?): ProfileResponse

    @POST("SendOTP")
    suspend fun sendOtp(@Body sendOtpRequest: SendOtpRequest): SendOtpResponse

    @POST("HomeService")
    suspend fun fetchHomeData(@Body homeRequest: HomeRequest): HomeResponse

    @Multipart
    @POST("UploadProFilePhoto")
    suspend fun uploadProfilePhoto(@Part("user_id")id:RequestBody,@Part image: MultipartBody.Part): ProfilePictureResponse
}
