package com.example.feature_stock_price.base

import io.reactivex.rxjava3.core.Scheduler

data class ThreadExecutors(
    val dbThreadExecutor: AppExecutor,
    val apiThreadExecutor: AppExecutor,
    val uiThreadExecutor: AppExecutor
)


interface AppExecutor {
    val scheduler: Scheduler

    /**
     * Simple implementation for Rx thread pools
     */
    class RxScheduler(override val scheduler: Scheduler) :
        AppExecutor
}


fun Scheduler.toAppExecutor(): AppExecutor =
    AppExecutor.RxScheduler(this)