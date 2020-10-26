package com.example.feature_stock_price

class StockDvo(
    val title: String,
    val subTitle: String,
    val imageUrl: String,
    val percent: String,
    val difference: String,
    val color: COLORED
) {

    enum class COLORED {
        RED, GREEN
    }
}