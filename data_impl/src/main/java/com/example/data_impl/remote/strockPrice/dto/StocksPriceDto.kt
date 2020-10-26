package com.example.data_impl.remote.strockPrice.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StocksPriceDto(
    @Expose @SerializedName("q") val stockPrices: List<StockPriceDto>
)