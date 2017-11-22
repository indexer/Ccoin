package com.indexer.ccoin.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
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

    private fun insertData(coins: ArrayList<Coin>) {
        Observable.just(mAppDatabase)
                .subscribeOn(Schedulers.io())
                .subscribe { db ->
                    db.coinDao.insertAllCoin(coins)
                }
    }

    fun fetchDataFromCurrencyCompare() {

        val coinList = RestClient.getService(getApplication())
                .getCoinList()
        coinList.enqueue(success = { response ->
            if (response.isSuccessful) {
                for ((_, value) in response.body()?.data!!.entries) {
                    mList.add(value)
                }
                insertData(mList)
            }

        }, failure = { _ ->
        })
    }


}





