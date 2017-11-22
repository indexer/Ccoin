package com.indexer.ccoin.view.dashboard.dashboard

import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.indexer.ccoin.CcoinApplication
import com.indexer.ccoin.R
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.model.Coin
import com.indexer.ccoin.viewmodel.CoinListViewModel
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinListViewModel

    @Inject
    lateinit var mAppDatabase: AppDatabase

    @Inject
    lateinit var mApplication: Application


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as CcoinApplication).getAppComponent().inject(this)
        val coins = ArrayList<Coin>()

        viewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)
        viewModel.fetchDataFromCurrencyCompare()
        viewModel.isDataBaseNotCreate().observe(this, Observer {
            Timber.e("isCreate", "" + mApplication + " " + mAppDatabase.isDatabaseCreated.value)
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




