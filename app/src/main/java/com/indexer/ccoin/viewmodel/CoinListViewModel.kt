package com.indexer.ccoin.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import com.indexer.ccoin.api.RestClient
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.model.Coin
import com.indexer.ccoin.utils.enqueue
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.ArrayList


class CoinListViewModel(application: Application) : AndroidViewModel(application) {

    private var mAppDatabase: AppDatabase = AppDatabase
            .getDatabase(application.applicationContext)

    private var mList = ArrayList<Coin>()


    fun isDataBaseNotCreate(): LiveData<Boolean> {
        return mAppDatabase.isDatabaseCreated
    }

    fun insertData(coins: List<Coin>) {
        io.reactivex.Observable.fromCallable<List<Coin>> {
            mAppDatabase.coinDao.insertAllCoin(coins)
            null
        }.observeOn(AndroidSchedulers.mainThread()).subscribe()
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

        }, failure = { response ->
            Log.e("Response", response.message)
        })
    }


}





