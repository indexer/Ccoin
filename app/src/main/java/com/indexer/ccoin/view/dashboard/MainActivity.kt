package com.indexer.ccoin.view.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.indexer.ccoin.CcoinApplication
import com.indexer.ccoin.R
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.viewmodel.CoinListViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var conViewModel: CoinListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as CcoinApplication).getAppComponent().inject(this)
        conViewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)
        conViewModel.getCoins().observe(this, Observer {
            when {it?.size == 0 -> conViewModel.fetchDataFromCurrencyCompare()
            }
        })
    }

    override fun setContentView(layoutResID: Int) {
        R.layout.activity_main
    }

    override fun onDestroy() {
        super.onDestroy()
        AppDatabase.destroyInstance()
    }
}




