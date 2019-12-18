package com.app.facepro.faceproschool.common

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)

    fun saveInPreference(key: String, value: Any) {
        sharedPreferences.edit {
            when (value) {
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
            }
        }
    }

    fun  <T> getFromPreference(key: String,default:T): T =
        when (default) {
            is Int -> sharedPreferences.getInt(key, default) as T
            is Boolean -> sharedPreferences.getBoolean(key, default) as T
            is String -> sharedPreferences.getString(key, default) as T
            is Float -> sharedPreferences.getFloat(key, default) as T
            else -> 0 as T
        }

    fun clearData() {
        sharedPreferences.edit{
            clear().apply()
        }
    }
}
