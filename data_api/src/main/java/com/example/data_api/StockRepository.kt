package com.example.data_api

import com.example.domain_api.model.RowStockPriceModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface StockRepository {

    fun connect(): Completable

    fun disconnect(): Completable

    fun observeStockPrice(): Flowable<List<RowStockPriceModel>>

}