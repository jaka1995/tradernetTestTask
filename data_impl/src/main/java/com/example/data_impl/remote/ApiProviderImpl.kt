package com.example.data_impl.remote

import com.example.data_impl.remote.strockPrice.StockRateApi
import com.example.data_impl.remote.strockPrice.impl.StockRateApiImpl
import com.google.gson.Gson
import javax.inject.Inject

class ApiProviderImpl @Inject constructor(
    private val gson: Gson
) : ApiProvider {
    override fun provideStockRateApi(): StockRateApi {
        return StockRateApiImpl(gson)
    }
}