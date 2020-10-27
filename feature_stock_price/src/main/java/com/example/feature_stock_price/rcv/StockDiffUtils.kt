package com.example.feature_stock_price.rcv

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_stock_price.ui.StockDvo

class StockDiffUtils(
    private val old: List<StockDvo>,
    private val new: List<StockDvo>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTitle = old[oldItemPosition].title
        val newTitle = new[newItemPosition].title
        return oldTitle == newTitle
    }

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPercent = old[oldItemPosition].percent
        val newPercent = new[newItemPosition].percent
        val oldDiffer =  old[oldItemPosition].difference
        val newDiffer = new[newItemPosition].difference
        return oldPercent == newPercent &&
                oldDiffer == newDiffer

    }
}