# Real time stocks.

We're working on a startup that is a Stock Manager! Currently, we're being
asked to develop a screen where you can see the stocks that the business
has deemed as featured and where we can show how their prices fluctuate.

## Description

### Stock
A stock consists of a Ticker (it's stock market identifier like AAPL),
it's current price (a double), and the name of the company behind that stock.

### Scope
A teammate has already worked on the ViewModel and the repository to retrieve
the featured stocks, our task is to focus only on the UI of this screen.

In `FeaturedStocksFragment` we observe a live data that emits a List<Stock>
every 10 seconds. Display that list in a screen in a way that efficiently
handles stock updates. Take a look at `real-time-stocks-mockup.png` for an
idea of how the screen might look like.
