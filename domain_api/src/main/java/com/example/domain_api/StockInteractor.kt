package com.example.domain_api

import com.example.domain_api.model.StockPriceModel
import io.reactivex.rxjava3.core.Observable

interface StockInteractor {

    fun observeStockPrice(): Observable<StockPriceModel>
}