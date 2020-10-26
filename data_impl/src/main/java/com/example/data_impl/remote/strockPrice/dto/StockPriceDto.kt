package com.example.data_impl.remote.strockPrice.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StockPriceDto (
    @Expose @SerializedName("c")        val tikcer: String? = null,
    @Expose @SerializedName("name")     val stockName: String? = null,
    @Expose @SerializedName("op")       val openPrice: Double? = null,
    @Expose @SerializedName("ltp")      val lastPrice: Double? = null,
    @Expose @SerializedName("min_step") val min_step: Double? = null
)