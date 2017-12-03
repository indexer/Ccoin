package com.indexer.ccoin.view.dashboard.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import android.content.Context
import android.util.Log
import com.indexer.ccoin.api.RestClient
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.model.Coin
import com.indexer.ccoin.utils.enqueue
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList


class CoinListViewModel(application: Application) : AndroidViewModel(application) {

    private var mList = ArrayList<Coin>()

    fun isDataBaseNotCreate(appDatabase: AppDatabase?):
            LiveData<Boolean>? = appDatabase?.isDatabaseCreated


    fun getCoinsWithPage(appDatabase: AppDatabase?):
            LiveData<PagedList<Coin>>? = appDatabase?.coinDao
            ?.getAllCoinListWithPage()?.create(0,
            PagedList.Config.Builder().setPageSize(10).setEnablePlaceholders(false)
                    .setPrefetchDistance(5).build())

    private fun insertData(coins: ArrayList<Coin>,
                           appDatabase: AppDatabase?) {
        Observable.just(appDatabase)
                .subscribeOn(Schedulers.io())
                .subscribe { it: AppDatabase? ->
                    it?.coinDao?.insertAllCoin(coins)
                }
    }

    fun fetchDataFromCurrencyCompare(appDatabase: AppDatabase?, context: Context) {
        val coinList = RestClient.getService(context)
                .getCoinList()
        coinList.enqueue(success = {
            if (it.isSuccessful) {
                it.body()?.data!!.entries.forEach { (_, value) -> mList.add(value) }
                insertData(coins = mList, appDatabase = appDatabase)
            }

        }, failure = { })
    }


}





