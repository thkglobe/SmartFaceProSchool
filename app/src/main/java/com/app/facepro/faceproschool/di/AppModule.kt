package com.app.facepro.faceproschool.di

import com.app.facepro.faceproschool.api.ApiService
import com.app.facepro.faceproschool.common.PreferenceManager
import com.app.facepro.faceproschool.ui.SharedViewModel
import com.app.facepro.faceproschool.ui.home.HomeRepository
import com.app.facepro.faceproschool.ui.home.HomeViewModel
import com.app.facepro.faceproschool.ui.login.LoginRepository
import com.app.facepro.faceproschool.ui.login.LoginViewModel
import com.app.facepro.faceproschool.ui.notifications.NotificationRepository
import com.app.facepro.faceproschool.ui.notifications.NotificationsViewModel
import com.app.facepro.faceproschool.ui.otp.OtpRepository
import com.app.facepro.faceproschool.ui.otp.OtpViewModel
import com.app.facepro.faceproschool.ui.profile.ProfileRepository
import com.app.facepro.faceproschool.ui.profile.ProfileViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SharedViewModel() }
    viewModel { NotificationsViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { OtpViewModel(get()) }
    viewModel { HomeViewModel(get(),get()) }
    factory { get<Retrofit>().create(ApiService::class.java) }
    factory { LoginRepository(get()) }
    factory { NotificationRepository(get()) }
    factory { ProfileRepository(get()) }
    factory { OtpRepository(get()) }
    factory { HomeRepository(get()) }
    single { PreferenceManager(get()) }
    factory {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }
    factory {
        Retrofit.Builder()
            .client(get())
            .baseUrl("http://embedcon.com/EmbdSecService/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
