package com.indexer.ccoin.view.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.indexer.ccoin.CcoinApplication
import com.indexer.ccoin.R
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.utils.SpacesItemDecoration
import com.indexer.ccoin.view.dashboard.adapter.CoinListAdapter
import com.indexer.ccoin.view.dashboard.detail.DetailActivity
import com.indexer.ccoin.view.dashboard.listener.OnItemClickListener
import com.indexer.ccoin.view.dashboard.viewmodel.CoinListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), OnItemClickListener {

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    private lateinit var conViewModel: CoinListViewModel

    @Inject
    lateinit var mAppDatabase: AppDatabase

    private lateinit var coinListAdapter: CoinListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as CcoinApplication).getAppComponent().inject(this)
        setContentView(R.layout.activity_main)
        setUpViewModel()
        setUpRecyclerViewAndAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppDatabase.destroyInstance()
    }

    private fun setUpViewModel() {
        conViewModel = ViewModelProviders.of(this)
                .get(CoinListViewModel::class.java)
        with(conViewModel) {
            conViewModel.getCoinsWithPage(mAppDatabase)?.observe(this@MainActivity, Observer {
                if (it?.size!! > 0) {
                    mprogress.visibility = View.GONE
                    coin_name.visibility = View.VISIBLE
                    coinListAdapter.setList(it)
                } else {
                    fetchDataFromCurrencyCompare(mAppDatabase)
                }
            })
            /* conViewModel.getMultipleIds(mAppDatabase)?.observe(this@MainActivity, Observer {
          Log.e("size in muptiple", "" + it?.size)
      })*/

        }
    }

    private fun setUpRecyclerViewAndAdapter() {
        coinListAdapter = CoinListAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        coin_name.layoutManager = linearLayoutManager
        coin_name.adapter = coinListAdapter
        val dividerItemDecoration = SpacesItemDecoration(8)
        coin_name.addItemDecoration(dividerItemDecoration)
    }
}




