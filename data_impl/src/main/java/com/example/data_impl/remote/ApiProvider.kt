package com.example.data_impl.remote

import com.example.data_impl.remote.strockPrice.StockRateApi

interface ApiProvider {

    fun provideStockRateApi(): StockRateApi
}