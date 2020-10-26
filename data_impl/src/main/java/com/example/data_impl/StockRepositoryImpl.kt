package com.example.data_impl

import com.example.data_api.StockRepository
import com.example.data_impl.remote.ApiProvider
import com.example.domain_api.model.StockPriceModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val apiProvider: ApiProvider,
    private val mapper: StockPriceMapper
) : StockRepository {
    override fun observeStockPrice(): Flowable<List<StockPriceModel>> {
        return apiProvider
            .provideStockRateApi()
            .observeStockPrice()
            .map {
                mapper.map(it)
            }
    }

    override fun connect(): Completable {
        return apiProvider
            .provideStockRateApi()
            .connect()
    }

    override fun disconnect(): Completable {
        return apiProvider
            .provideStockRateApi()
            .disconnect()
    }

}
