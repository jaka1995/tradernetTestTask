package com.example.data_impl.local.stockPrice

import com.example.data_impl.remote.strockPrice.dto.StockPriceDto

class StockDaoImpl : StockDao {

    private val tempsStocks = mutableListOf<StockPriceDto>()

    override fun provideLocalStockPrice(): List<StockPriceDto> {
        return tempsStocks
    }

    override fun saveStockPrice(stocksPriceDto: List<StockPriceDto>) {
        this.tempsStocks.clear()
        this.tempsStocks.addAll(stocksPriceDto)
    }

}