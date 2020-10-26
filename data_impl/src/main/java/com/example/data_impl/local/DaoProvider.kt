package com.example.data_impl.local

import com.example.data_impl.local.stockPrice.StockDao

interface DaoProvider {

    fun provideStockDao(): StockDao
}