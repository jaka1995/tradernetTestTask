package com.example.domain_impl.interactor

import com.example.data_api.StockRepository
import com.example.domain_api.StockInteractor
import com.example.domain_api.model.RoundedStockPriceModel
import com.example.domain_impl.helper.StockConverter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class StockInteractorImpl @Inject constructor(
    private val repo: StockRepository,
    private val converter: StockConverter
) : StockInteractor {
    override fun connect(): Completable {
        return repo.connect()
    }

    override fun disconnect(): Completable {
        return repo.disconnect()
    }


    override fun observeStockPrice(): Flowable<List<RoundedStockPriceModel>> {
        return repo
            .observeStockPrice()
            .map {
                it.map { converter.convert(it) }
            }
    }

}