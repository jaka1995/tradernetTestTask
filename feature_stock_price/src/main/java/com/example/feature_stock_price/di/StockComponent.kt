package com.example.feature_stock_price.di

import com.example.domain_api.StockInteractor
import com.example.feature_stock_price.base.ThreadExecutors
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StockModule::class])
interface StockComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun interactor(interactor: StockInteractor): Builder

        fun build(): StockComponent
    }

    fun threadExecutors(): ThreadExecutors

    fun interactor(): StockInteractor
}


object StockComponentImpl {
    private lateinit var _stockComponent: StockComponent

    val stockComponent get() = _stockComponent

    fun init(stockComponent: StockComponent) {
        _stockComponent = stockComponent
    }

    fun inject(
        interactor: StockInteractor
    ): StockComponent {

        val stockComponent = DaggerStockComponent.builder()
            .interactor(interactor)
            .build()

        init(stockComponent)

        return stockComponent
    }
}


val Any.StockComponentInjector: StockComponent by lazy { StockComponentImpl.stockComponent }