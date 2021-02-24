package com.example.myapplication.di

import android.app.Application
import android.content.res.Configuration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin(){
        val appModule = mutableListOf<Module>(module, serviceModule)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CustomApplication)
            modules(appModule)
        }
    }
}