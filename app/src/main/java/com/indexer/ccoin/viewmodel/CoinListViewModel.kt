package com.indexer.ccoin.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import com.indexer.ccoin.api.RestClient
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.utils.enqueue


class CoinListViewModel(application: Application) : AndroidViewModel(application) {


    var mAppDatabase: AppDatabase = AppDatabase.getDatabase(application.applicationContext)


    fun isDataBaseNotCreate(): LiveData<Boolean> {
        return mAppDatabase.isDatabaseCreated
    }

    fun fetchDataFromCurrencyCompare() {
        val coinList = RestClient.getService(getApplication())
                .getCoinList()
        coinList.enqueue(success = { response ->
            Log.e("isResponse", response.message())
            if (response.isSuccessful) {

            }

        }, failure = { response ->
            Log.e("Response", response.message)
        })
    }


}





