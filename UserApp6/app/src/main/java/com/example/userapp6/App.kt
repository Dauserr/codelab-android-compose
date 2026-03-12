package com.example.userapp6

import android.app.Application
import com.example.userapp6.di.AppComponent
import com.example.userapp6.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
