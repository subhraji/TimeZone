package com.example.timezone.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.timezone.MyApplication

object PrefManager {

    private var sharedPreferences: SharedPreferences = MyApplication.application.applicationContext.getSharedPreferences(
        "",
        Context.MODE_PRIVATE
    )

    // Keys
    const val IS_LOGIN = "isLogin"



    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun getInt(key: String): Int? {
        return sharedPreferences.getInt(key,0)
    }

    fun putString(key: String, value: String?) {
        var mEditor: SharedPreferences.Editor
        mEditor = sharedPreferences.edit()
        mEditor.putString(key, value)
        mEditor.commit()
    }

    fun putInt(key: String, value: Int?) {
        var mEditor: SharedPreferences.Editor
        mEditor = sharedPreferences.edit()
        value?.let {
            mEditor.putInt(key, it)
            mEditor.commit()
        }

    }

    fun putBoolean(key: String, value: Boolean?) {
        var mEditor: SharedPreferences.Editor
        mEditor = sharedPreferences.edit()
        value?.let { mEditor.putBoolean(key, it) }
        mEditor.commit()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun clearPref(){
        val editor = sharedPreferences.edit()
        editor.remove(IS_LOGIN)
        editor.apply()
    }


    fun getLogInStatus(): Boolean? {
        return getBoolean(IS_LOGIN)
    }

    fun setLogInStatus(loginStatus: Boolean) {
        putBoolean(IS_LOGIN, loginStatus)
    }

}