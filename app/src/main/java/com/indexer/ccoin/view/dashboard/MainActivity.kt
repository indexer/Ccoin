package com.indexer.ccoin.view.dashboard

import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.indexer.ccoin.CcoinApplication
import com.indexer.ccoin.R
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.view.dashboard.viewmodel.CoinListViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private lateinit var conViewModel: CoinListViewModel

    @Inject
    lateinit var mAppDatabase: AppDatabase

    @Inject
    lateinit var mApplication: Application

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as CcoinApplication).getAppComponent().inject(this)
        conViewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)
        conViewModel.getAllcoins(appDatabase = mAppDatabase)
        conViewModel.fetchDataFromCurrencyCompare(mAppDatabase)
        conViewModel.getCoinsWithPage(mAppDatabase)?.observe(this, Observer {
            Log.e("total", "=" + it?.size)
            /*when {it?.size == 0 ->
            }*/

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




