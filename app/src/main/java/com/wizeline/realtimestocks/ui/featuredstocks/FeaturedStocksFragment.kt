package com.wizeline.realtimestocks.ui.featuredstocks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wizeline.realtimestocks.databinding.FragmentFeaturedStocksBinding
import com.wizeline.realtimestocks.stock.Stock
import kotlinx.android.synthetic.main.fragment_featured_stocks.rvStocks
import kotlinx.coroutines.ExperimentalCoroutinesApi

class FeaturedStocksFragment : Fragment() {

    private var _binding: FragmentFeaturedStocksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FeaturedStocksViewModel by viewModels()

    private val adapter by lazy {
        StocksAdapter()
    }

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeaturedStocksBinding.inflate(inflater, container, false)
        viewModel.stocks.observe(viewLifecycleOwner) { stocks ->
            onStocks(stocks)
        }
        return binding.root
    }

    // Called when the fragment's view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sets the adapter for the RecyclerView
        with(_binding) {
            rvStocks.adapter = adapter  // Assigns the adapter to the RecyclerView
        }
    }

    // Function to handle incoming stock data
    private fun onStocks(stocks: List<Stock>) {
        adapter.submitList(stocks)  // Submits the list of stocks to the adapter for display
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
