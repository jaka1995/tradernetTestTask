package com.example.data_impl

import com.example.data_impl.remote.strockPrice.dto.StocksPriceDto
import com.example.domain_api.model.RowStockPriceModel
import java.util.*

class StockPriceMapper {

    companion object {
        private const val IMAGE_URL = "https://tradernet.ru/logos/get-logo-by-ticker?ticker="
    }

    fun map(dto: StocksPriceDto): List<RowStockPriceModel> {
        val tempList = mutableListOf<RowStockPriceModel>()

        dto.stockPrices.forEach {
            val temp = RowStockPriceModel(
                tickerImage = IMAGE_URL + it.tikcer?.toLowerCase(Locale.ROOT),
                tickerLastPrice = it.lastPrice ?: 0.0,
                tickerIdentifier = it.tikcer ?: "",
                tickerName = it.stockName ?: "",
                tickerOpenPrice = it.openPrice ?: 0.0,
                tickerPriceMinStep = it.min_step ?: 0.0
            )

            tempList.add(temp)
        }
        return tempList
    }

}