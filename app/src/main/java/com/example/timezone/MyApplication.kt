package com.example.timezone

import android.app.Application
class MyApplication : Application() {

//    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.create()
//    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        lateinit var application: Application

        fun getInstance(): Application {
            return application
        }
    }

}