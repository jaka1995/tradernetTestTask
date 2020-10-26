package com.example.data_api

import com.example.domain_api.model.StockPriceModel
import io.reactivex.rxjava3.core.Observable

interface StockRepository {

    fun observeStockPrice(): Observable<StockPriceModel>
}