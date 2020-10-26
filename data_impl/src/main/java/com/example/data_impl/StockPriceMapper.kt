package com.example.data_impl

import com.example.data_impl.remote.strockPrice.dto.StocksPriceDto
import com.example.domain_api.model.StockPriceModel
import java.util.*
import kotlin.math.roundToInt

class StockPriceMapper {

    companion object {
        private const val IMAGE_URL = "https://tradernet.ru/logos/get-logo-by-ticker?ticker="
    }

    fun map(dto: StocksPriceDto): List<StockPriceModel> {
        val tempList = mutableListOf<StockPriceModel>()

        dto.stockPrices.forEach {
            val temp = StockPriceModel(
                imageUrl = IMAGE_URL + it.tikcer?.toLowerCase(Locale.ROOT),
                ticker = it.tikcer ?: "",
                difference = (it.openPrice ?: 0.0) / (it.lastPrice ?: 0.0) * 100,
                lastPrice = it.lastPrice ?: 0.0,
                stockName = it.stockName ?: "",
                priceDifference = round(
                    value = (it.openPrice ?: 0.0) - (it.lastPrice ?: 0.0),
                    step = it.min_step ?: 0.0
                )
            )

            tempList.add(temp)
        }
        return tempList
    }

    private fun round(value: Double, step: Double): Double {
        return (value / step).roundToInt() * step
    }
}