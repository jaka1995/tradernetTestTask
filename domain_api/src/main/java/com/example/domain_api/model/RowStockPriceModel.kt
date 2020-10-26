package com.example.domain_api.model

class RowStockPriceModel(
    val tickerName: String,
    val tickerImage: String,
    val tickerIdentifier: String,
    val tickerOpenPrice: Double,
    val tickerLastPrice: Double,
    val tickerPriceMinStep: Double
)