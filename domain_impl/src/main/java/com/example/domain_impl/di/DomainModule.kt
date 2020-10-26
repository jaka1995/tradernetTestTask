package com.example.domain_impl.di

import com.example.domain_api.StockInteractor
import com.example.domain_impl.interactor.StockInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun provideStockInteractor(interactor: StockInteractorImpl): StockInteractor
}

