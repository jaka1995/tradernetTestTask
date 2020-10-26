package com.example.domain_impl.di

import com.example.domain_impl.helper.StockConverter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainDepsModule {

    @Singleton
    @Provides
    fun provideConverter(): StockConverter {
        return StockConverter()
    }
}