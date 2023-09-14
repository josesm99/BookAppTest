package com.example.bookapptest.application

import android.app.Application
import com.example.bookapptest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinExampleApplication)
            modules(appModule)
        }
    }
}