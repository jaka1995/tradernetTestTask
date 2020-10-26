package com.example.domain_api.model

class StockPriceModel(
    val imageUrl: String,
    val ticker: String,
    val difference: Double,
    val lastPrice: Double,
    val stockName: String,
    val priceDifference: Double
)