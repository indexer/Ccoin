package com.indexer.ccoin.database

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.persistence.room.Query
import com.indexer.ccoin.model.Coin
import android.arch.paging.PagedList
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy


/**
 * Created by indexer on 12/11/17.
 */
@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCoin(products: List<Coin>)

    @Query("SELECT * FROM coin ORDER BY SortOrder ASC")
    fun getAllCoinListWithPage(): DataSource.Factory<Int, Coin>

    @Query("SELECT * FROM coin")
    fun getAllCoin(): List<Coin>

    @Query("SELECT * FROM coin WHERE coinId IN (:coinIds)")
    fun findByCoinIds(coinIds: List<String>): LiveData<List<Coin>>
}