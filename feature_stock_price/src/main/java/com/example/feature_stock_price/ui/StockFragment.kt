package com.example.feature_stock_price.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.feature_stock_price.R
import com.example.feature_stock_price.rcv.StockAdapter
import com.example.feature_stock_price.vm.StockViewModel
import com.example.feature_stock_price.vm.StockViewModelFactory
import kotlinx.android.synthetic.main.fragment_stocks.*

class StockFragment : Fragment() {

    private val vRcv get() = fragment_stocks_rcv
    private val adapter = StockAdapter()
    private val viewModel by lazy { StockViewModelFactory().create(StockViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stocks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vRcv.adapter = adapter

        viewModel.observeStocks()
            .observe(viewLifecycleOwner, Observer {
                adapter.setNewItems(it)
            })
    }
}