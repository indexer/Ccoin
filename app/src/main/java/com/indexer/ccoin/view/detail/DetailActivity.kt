package com.indexer.ccoin.view.dashboard.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.indexer.ccoin.R
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.viewmodel.CoinListViewModel


class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java!!)
        viewModel.fetchDataFromCurrencyCompare()
        viewModel.isDataBaseNotCreate().observe(this, Observer {

        })


    }

    override fun onDestroy() {
        super.onDestroy()
        AppDatabase.destroyInstance()
    }
}




