package com.wizeline.realtimestocks.ui.featuredstocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wizeline.realtimestocks.stock.Stock
import com.wizeline.realtimestocks.stock.StocksGenerator
import com.wizeline.realtimestocks.stock.StocksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class FeaturedStocksViewModel : ViewModel() {

    private val repository = StocksRepository(Dispatchers.IO, StocksGenerator())

    @ExperimentalCoroutinesApi
    val stocks: LiveData<List<Stock>> = repository
        .getFeaturedStocks(delayInMillis = 10_000)
        .asLiveData()

    override fun onCleared() {
        repository.close()
    }
}