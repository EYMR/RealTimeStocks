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

    // ViewHolder for a single stock item
    inner class StocksViewHolder(
        private val itemBinding: ItemStocksBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(stock: Stock) {
            // Binds stock data to the views in the ViewHolder
            with(itemBinding) {
                tvCompanyName.text = stock.companyName
                tvPrice.text = stock.price.formatAsCurrency()  // Assuming formatAsCurrency() is defined elsewhere
                tvTicker.text = stock.ticker
            }
        }
    }

    // Called when a new ViewHolder is needed to display a stock item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksViewHolder {
        // Inflates the stock item layout and creates a ViewHolder
        return StocksViewHolder(
            ItemStocksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    // Called to bind a stock item to a ViewHolder
    override fun onBindViewHolder(holder: StocksViewHolder, position: Int) {
        // Retrieves the stock at the given position and calls the ViewHolder's bind method
        val stock = getItem(position)
        holder.bind(stock)
    }

    // DiffUtil callback for efficient updates
    class DiffCallbackStocks : DiffUtil.ItemCallback<Stock>() {
        override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            // Checks if two stock items are the same instance (by reference)
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            // Checks if two stock items have the same company name and ticker,
            // indicating that their contents are the same
            return oldItem.companyName == newItem.companyName && oldItem.ticker == newItem.ticker
        }
    }
}
