package com.example.feature_stock_price.rcv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_stock_price.R
import com.example.feature_stock_price.ui.StockDvo

class StockAdapter : RecyclerView.Adapter<StockAdapter.StockAdapterViewHolder>() {

    private val items: MutableList<StockDvo> = mutableListOf()

    fun setNewItems(newList: List<StockDvo>) {
        val diffUtilsResult = DiffUtil.calculateDiff(
            StockDiffUtils(
                items,
                newList
            )
        )
        diffUtilsResult.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockAdapterViewHolder {
        return StockAdapterViewHolder(
            LayoutInflater.from(
                parent.context
            )
                .inflate(
                    R.layout.item_stock_price,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: StockAdapterViewHolder, position: Int) {
        try {
            val item = items[position]
            holder.title.text = item.title
            holder.subTitle.text = item.subTitle
            holder.title.text = item.title
            holder.difference.text = item.difference
            holder.percent.text = item.percent

            when (item.color) {
                StockDvo.COLORED.RED -> {
                    holder.percent.setBackgroundResource(R.drawable.bg_rounded_red)
                }
                StockDvo.COLORED.GREEN -> {
                    holder.percent.setBackgroundResource(R.drawable.bg_rounded_green)
                }
            }

            Glide
                .with(holder.itemView)
                .load(item.imageUrl)
                .into(holder.image)

        } catch (e: ArrayIndexOutOfBoundsException) {

        }
    }

    class StockAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.findViewById(R.id.item_stock_price_title)
        val subTitle: AppCompatTextView = itemView.findViewById(R.id.item_stock_price_subtitle)
        val image: AppCompatImageView = itemView.findViewById(R.id.item_stock_price_image)
        val difference: AppCompatTextView = itemView.findViewById(R.id.item_stock_price_difference)
        val percent: AppCompatTextView = itemView.findViewById(R.id.item_stock_price_percent)
    }
}

