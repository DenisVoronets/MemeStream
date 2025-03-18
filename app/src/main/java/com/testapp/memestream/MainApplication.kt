package com.testapp.memestream

import android.app.Application
import com.testapp.memestream.di.initializeKoin

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }
}