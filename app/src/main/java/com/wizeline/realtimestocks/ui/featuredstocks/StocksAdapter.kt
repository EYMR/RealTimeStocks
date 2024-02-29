package com.wizeline.realtimestocks.ui.featuredstocks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.realtimestocks.databinding.ItemStocksBinding
import com.wizeline.realtimestocks.stock.Stock
import com.wizeline.realtimestocks.util.formatAsCurrency

class StocksAdapter : ListAdapter<Stock, StocksAdapter.StocksViewHolder>(DiffCallbackStocks()) {


    inner class StocksViewHolder(
        private val itemBinding: ItemStocksBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(stock: Stock) {
            with(itemBinding) {
                tvCompanyName.text = stock.companyName
                tvPrice.text = stock.price.formatAsCurrency()
                tvTicker.text = stock.ticker
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksViewHolder {
        return StocksViewHolder(
            ItemStocksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StocksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallbackStocks : DiffUtil.ItemCallback<Stock>() {
        override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem.companyName == newItem.companyName && oldItem.ticker == oldItem.ticker
        }

    }
}