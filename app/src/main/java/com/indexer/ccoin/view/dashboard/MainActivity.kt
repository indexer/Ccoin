package com.indexer.ccoin.view.dashboard

import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.indexer.ccoin.CcoinApplication
import com.indexer.ccoin.R
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.view.dashboard.adapter.CoinListAdapter
import com.indexer.ccoin.view.dashboard.viewmodel.CoinListViewModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var conViewModel: CoinListViewModel

    @Inject
    lateinit var mAppDatabase: AppDatabase

    private lateinit var coinListAdapter: CoinListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as CcoinApplication).getAppComponent().inject(this)
        setContentView(R.layout.activity_main)
        coinListAdapter = CoinListAdapter()

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        coin_name.layoutManager = linearLayoutManager
        coin_name.adapter = coinListAdapter

        conViewModel = ViewModelProviders.of(this).get(CoinListViewModel::class.java)
        with(conViewModel) {
            fetchDataFromCurrencyCompare(mAppDatabase, this@MainActivity)
            conViewModel.getCoinsWithPage(mAppDatabase)?.observe(this@MainActivity, Observer {
                coinListAdapter.setList(it)
            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        AppDatabase.destroyInstance()
    }
}




