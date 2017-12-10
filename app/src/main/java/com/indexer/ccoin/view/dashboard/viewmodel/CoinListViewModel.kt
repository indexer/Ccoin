package com.indexer.ccoin.view.dashboard.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.DataSource.Factory
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.arch.paging.PagedList.*
import com.indexer.ccoin.api.RestClient
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.model.Coin
import com.indexer.ccoin.utils.enqueue
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList


class CoinListViewModel(application: Application) : AndroidViewModel(application) {

    private var mList = ArrayList<Coin>()
    private val list = arrayListOf("1182", "7605", "5038",
            "24854", "3807", "3808", "202330", "5324", "5031", "20131")

    fun isDataBaseNotCreate(appDatabase: AppDatabase?):
            LiveData<Boolean>? = appDatabase?.isDatabaseCreated

    fun getMultipleIds(appDatabase: AppDatabase?):
            LiveData<List<Coin>>? = appDatabase?.coinDao?.findByCoinIds(list)

    fun getAllValue(appDatabase: AppDatabase?): List<Coin> {

        val coinTotal = Observable.just(appDatabase).subscribeOn(Schedulers.io()).map { it ->
            it.coinDao.getAllCoin()
        }.blockingFirst() as ArrayList<Coin>
        System.out.println("mList" + Observable.just(appDatabase).subscribeOn(Schedulers.io()).map { it ->
            it.coinDao.getAllCoin()
        }.blockingFirst() as ArrayList<Coin>)
        return coinTotal

    }


    fun getCoinsWithPage(appDatabase: AppDatabase?):
            LiveData<PagedList<Coin>>? = appDatabase?.coinDao?.getAllCoinListWithPage()?.let {
        LivePagedListBuilder(it, Config.Builder().setPageSize(100).setEnablePlaceholders(false)
                .setPrefetchDistance(10).build()).setInitialLoadKey(0).build()
    }


    private fun insertData(coins: ArrayList<Coin>,
                           appDatabase: AppDatabase?) {
        Observable.just(appDatabase).subscribeOn(Schedulers.io()).map { it ->
            it.coinDao.insertAllCoin(coins)
        }.subscribe()

    }

    fun fetchDataFromCurrencyCompare(appDatabase: AppDatabase?) {
        val coinList = RestClient.getService()
                .getCoinList()
        coinList.enqueue(success = {
            if (it.isSuccessful) {
                it.body()?.data!!.entries.forEach { (_, value) -> mList.add(value) }
                insertData(coins = mList, appDatabase = appDatabase)
            }

        }, failure = { })
    }


}





