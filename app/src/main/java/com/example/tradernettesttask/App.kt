package com.example.tradernettesttask

import android.app.Application
import com.example.data_impl.di.DaggerDataComponent

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        val dataComponent = DaggerDataComponent.create()
    }
}