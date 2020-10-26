package com.example.data_impl.di

import com.example.data_api.StockRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, DataModule::class])
@Singleton
interface DataComponent {

    fun provideStockRepository(): StockRepository
}