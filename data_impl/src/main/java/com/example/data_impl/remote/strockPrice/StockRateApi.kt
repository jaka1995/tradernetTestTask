package com.example.data_impl.remote.strockPrice

import com.example.data_impl.remote.strockPrice.dto.StocksPriceDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface StockRateApi {

    fun connect(): Completable

    fun disconnect(): Completable

    fun observeStockPrice(): Flowable<StocksPriceDto>
}