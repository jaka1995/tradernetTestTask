package com.example.feature_stock_price.di

import com.example.feature_stock_price.base.ThreadExecutors
import com.example.feature_stock_price.base.toAppExecutor
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.internal.schedulers.ComputationScheduler
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import javax.inject.Singleton

@Module
class StockModule {

    @Singleton
    @Provides
    fun provideThreadExecutors(): ThreadExecutors =
        ThreadExecutors(
            ComputationScheduler().toAppExecutor(),
            IoScheduler().toAppExecutor(),
            AndroidSchedulers.mainThread().toAppExecutor()
        )
}