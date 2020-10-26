package com.example.tradernettesttask

import android.app.Application
import com.example.data_impl.di.DaggerDataComponent
import com.example.domain_impl.di.DaggerDomainComponent

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        val dataComponent = DaggerDataComponent.create()
        val domainComponent = DaggerDomainComponent.builder()
            .repo(dataComponent.provideStockRepository())
            .build()


    }
}