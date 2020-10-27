package com.example.data_impl.di

import com.example.data_impl.StockPriceMapper
import com.example.data_impl.local.stockPrice.StockDao
import com.example.data_impl.local.stockPrice.StockDaoImpl
import com.example.data_impl.remote.strockPrice.StockRateApi
import com.example.data_impl.remote.strockPrice.impl.StockRateApiImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataDepsModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideMapper(): StockPriceMapper {
        return StockPriceMapper()
    }

    @Singleton
    @Provides
    fun provideRatesApi(gson: Gson): StockRateApi {
        return StockRateApiImpl(gson)
    }

    @Singleton
    @Provides
    fun provideLocal(): StockDao {
        return StockDaoImpl()
    }
}