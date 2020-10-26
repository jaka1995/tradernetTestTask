package com.example.tradernettesttask

import android.app.Application
import com.example.data_impl.di.DaggerDataComponent
import com.example.domain_impl.di.DaggerDomainComponent
import com.example.feature_stock_price.di.StockComponentImpl

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        val dataComponent = DaggerDataComponent.create()
        val domainComponent = DaggerDomainComponent.builder()
            .repo(dataComponent.provideStockRepository())
            .build()

        val stockComponent = StockComponentImpl
            .inject(domainComponent.stockInteractor)

    }
}