package com.example.btechapp.presentation

import android.app.Application
import com.example.btechapp.di.CommonModule
import com.example.btechapp.di.ProductModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                CommonModule.create(),
                ProductModule.create()
            )
        }
    }

}