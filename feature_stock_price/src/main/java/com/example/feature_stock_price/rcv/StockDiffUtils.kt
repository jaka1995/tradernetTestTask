package com.example.feature_stock_price.rcv

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_stock_price.ui.StockDvo

class StockDiffUtils(
    private val old: List<StockDvo>,
    private val new: List<StockDvo>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].title == new[newItemPosition].title
    }

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].percent == new[newItemPosition].percent &&
                old[oldItemPosition].color == new[newItemPosition].color &&
                old[oldItemPosition].imageUrl == new[newItemPosition].title &&
                old[oldItemPosition].difference == new[newItemPosition].difference

    }
}