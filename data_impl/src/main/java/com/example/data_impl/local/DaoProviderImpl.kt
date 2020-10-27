package com.example.data_impl.local

import com.example.data_impl.local.stockPrice.StockDao
import javax.inject.Inject

class DaoProviderImpl @Inject constructor(
    private val stockDao: StockDao
) : DaoProvider {
    override fun provideStockDao(): StockDao {
        return stockDao
    }

}