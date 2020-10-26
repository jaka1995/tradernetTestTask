package com.example.data_impl.di

import com.example.data_api.StockRepository
import com.example.data_impl.StockRepositoryImpl
import com.example.data_impl.remote.ApiProvider
import com.example.data_impl.remote.ApiProviderImpl
import dagger.Binds
import dagger.Module

@Module
interface ApiModule {
    @Binds
    fun provideStockRepository(repo: StockRepositoryImpl): StockRepository

    @Binds
    fun provideApiProvider(apiProvider: ApiProviderImpl): ApiProvider
}