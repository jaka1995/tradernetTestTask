package com.example.domain_api

import com.example.domain_api.model.RoundedStockPriceModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface StockInteractor {

    fun connect(): Completable

    fun disconnect(): Completable

    fun observeStockPrice(): Flowable<List<RoundedStockPriceModel>>
}