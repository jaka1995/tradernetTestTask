package com.example.data_impl.di

import com.example.data_impl.StockPriceMapper
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
}