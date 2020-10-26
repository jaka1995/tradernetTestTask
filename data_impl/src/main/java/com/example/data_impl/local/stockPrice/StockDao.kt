package com.example.data_impl.local.stockPrice

import com.example.data_impl.remote.strockPrice.dto.StockPriceDto

interface StockDao {

    fun provideLocalStockPrice(): List<StockPriceDto>

    fun saveStockPrice(stocksPriceDto: List<StockPriceDto>)
}