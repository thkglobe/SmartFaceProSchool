package com.app.facepro.faceproschool


import android.app.Application
import com.app.facepro.faceproschool.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber



class FaceRecognizationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@FaceRecognizationApp)
            modules(appModule)
        }
    }

}
