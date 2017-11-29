package com.indexer.ccoin.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.indexer.ccoin.api.RestClient
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.model.Coin
import com.indexer.ccoin.utils.enqueue
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList


class CoinListViewModel(application: Application) : AndroidViewModel(application) {

    private var mAppDatabase: AppDatabase = AppDatabase
            .getDatabase(application.applicationContext)

    private var mList = ArrayList<Coin>()

    fun isDataBaseNotCreate(): LiveData<Boolean> = mAppDatabase.isDatabaseCreated

    fun getCoins(): LiveData<PagedList<Coin>> = mAppDatabase.coinDao
            .getAllCoinList().create(0,
            PagedList.Config.Builder().setPageSize(10).setEnablePlaceholders(false)
                    .setPrefetchDistance(5).build())

    private fun insertData(coins: ArrayList<Coin>) {
        Observable.just(mAppDatabase)
                .subscribeOn(Schedulers.io())
                .subscribe { it: AppDatabase? ->
                    it?.coinDao?.insertAllCoin(coins)
                }
    }

    fun fetchDataFromCurrencyCompare() {
        val coinList = RestClient.getService(getApplication())
                .getCoinList()
        coinList.enqueue(success = {
            if (it.isSuccessful) {
                it.body()?.data!!.entries.forEach { (_, value) -> mList.add(value) }
                insertData(coins = mList)
            }

        }, failure = { })
    }


}





