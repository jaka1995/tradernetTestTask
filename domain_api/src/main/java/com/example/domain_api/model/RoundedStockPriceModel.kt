package com.example.domain_api.model

class RoundedStockPriceModel(
    val tickerImage: String,
    val tickerIdentifier: String,
    val tickerDifferenceInPercent: Double,
    val tickerLastPrice: Double,
    val tickerName: String,
    val tickerPriceDifference: Double
)