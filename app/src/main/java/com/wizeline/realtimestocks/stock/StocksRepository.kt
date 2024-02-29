package com.wizeline.realtimestocks.stock

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

class StocksRepository(
    private val dispatcher: CoroutineDispatcher,
    private val generator: StocksGenerator
) {

    /**
     * Closes repository's [CoroutineDispatcher].
     */
    fun close() {
        dispatcher.cancel()
    }

    /**
     * Gets featured stocks.
     *
     * @param delayInMillis applied to resulting flow
     * @return flow containing a list of featured stocks sorted by ticker.
     */
    @ExperimentalCoroutinesApi
    fun getFeaturedStocks(delayInMillis: Long = 3000): Flow<List<Stock>> {
        return channelFlow {
            while (!isClosedForSend) {
                val stocks = getFeaturedStocks()
                send(stocks)
                delay(delayInMillis)
            }
        }.flowOn(dispatcher)
    }

    /**
     * Gets featured stocks.
     *
     * @return list of featured stocks sorted by ticker.
     */
    fun getFeaturedStocks(): List<Stock> {
        return generator.getStocks()
    }

    /**
     * Get a specific stock.
     *
     * @param stockTicker to look for.
     * @return stock that matches given stockTicker.
     */
    fun getStock(stockTicker: String): Stock? {
        return generator.getStock(stockTicker)
    }
}