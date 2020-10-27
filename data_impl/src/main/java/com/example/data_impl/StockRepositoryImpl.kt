package com.example.data_impl

import com.example.data_api.StockRepository
import com.example.data_impl.local.DaoProvider
import com.example.data_impl.remote.ApiProvider
import com.example.data_impl.remote.strockPrice.dto.StockPriceDto
import com.example.domain_api.model.RowStockPriceModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val apiProvider: ApiProvider,
    private val daoProvider: DaoProvider,
    private val mapper: StockPriceMapper
) : StockRepository {
    override fun observeStockPrice(): Flowable<List<RowStockPriceModel>> {
        return apiProvider
            .provideStockRateApi()
            .observeStockPrice()
            .map {
                val local = daoProvider.provideStockDao().provideLocalStockPrice()
                return@map syncDate(local, it.stockPrices)
            }
            .doOnNext {
                daoProvider
                    .provideStockDao()
                    .saveStockPrice(it)
            }
            .map {
                it.map { mapper.map(it) }
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

    private fun syncDate(
        local: List<StockPriceDto>,
        remote: List<StockPriceDto>
    ): List<StockPriceDto> {
        val finalList: MutableMap<String, StockPriceDto> = mutableMapOf()

        local.forEach {
            finalList.get(it.tikcer ?: "") ?: finalList.put(it.tikcer ?: "", it)
        }

        remote.forEach {
            finalList.put(it.tikcer ?: "", it)
        }

        return finalList.map { it.value }
    }

}
