package com.example.tradernettesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feature_stock_price.ui.StockFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val vContainer get() = fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()

        transaction
            .replace(vContainer.id, StockFragment())
            .commitAllowingStateLoss()


    }
}