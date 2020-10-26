package com.example.data_impl.remote

import com.example.data_impl.remote.strockPrice.StockRateApi
import javax.inject.Inject

class ApiProviderImpl @Inject constructor(
    private val rateApiImpl: StockRateApi
) : ApiProvider {
    override fun provideStockRateApi(): StockRateApi {
        return rateApiImpl
    }
}