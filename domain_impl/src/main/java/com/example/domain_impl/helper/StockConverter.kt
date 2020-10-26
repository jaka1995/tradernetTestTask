package com.example.domain_impl.helper

import com.example.domain_api.model.RoundedStockPriceModel
import com.example.domain_api.model.RowStockPriceModel
import kotlin.math.round
import kotlin.math.roundToInt

class StockConverter {

    companion object {
        private const val ROUNDING_DECIMAL = 2
    }

    fun convert(rowStockPriceModel: RowStockPriceModel): RoundedStockPriceModel {

        return RoundedStockPriceModel(
            tickerName = rowStockPriceModel.tickerName,
            tickerIdentifier = rowStockPriceModel.tickerIdentifier,
            tickerImage = rowStockPriceModel.tickerImage,
            tickerDifferenceInPercent = getDifferenceInPercent(
                rowStockPriceModel.tickerLastPrice,
                rowStockPriceModel.tickerOpenPrice
            ),
            tickerLastPrice = rowStockPriceModel.tickerLastPrice,
            tickerPriceDifference = getDifference(
                rowStockPriceModel.tickerLastPrice,
                rowStockPriceModel.tickerOpenPrice,
                rowStockPriceModel.tickerPriceMinStep
            )
        )
    }

    private fun getDifferenceInPercent(lastPrice: Double, openPrice: Double): Double {
        return (openPrice / lastPrice * 100).roundByDecimal(ROUNDING_DECIMAL)
    }

    private fun Double.roundByDecimal(decimal: Int): Double {
        var multiplier = 1.0
        repeat(decimal) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

    private fun getDifference(lastPrice: Double, openPrice: Double, minStep: Double): Double {
        return roundByMinStep(lastPrice - openPrice, minStep)
    }

    private fun roundByMinStep(value: Double, step: Double): Double {
        return (value / step).roundToInt() * step
    }
}