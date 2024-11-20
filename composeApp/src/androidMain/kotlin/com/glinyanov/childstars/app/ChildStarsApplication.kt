package com.glinyanov.childstars.app

import android.app.Application
import com.glinyanov.childstars.di.initKoin
import org.koin.android.ext.koin.androidContext

class ChildStarsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ChildStarsApplication)
        }
    }
}