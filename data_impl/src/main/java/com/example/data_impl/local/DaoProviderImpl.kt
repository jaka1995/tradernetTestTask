package com.example.data_impl.local

import com.example.data_impl.local.stockPrice.StockDao
import com.example.data_impl.local.stockPrice.StockDaoImpl
import javax.inject.Inject

class DaoProviderImpl @Inject constructor() : DaoProvider {
    override fun provideStockDao(): StockDao {
        return StockDaoImpl()
    }

}