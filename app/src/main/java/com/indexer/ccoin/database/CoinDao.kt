package com.indexer.ccoin.database

import android.arch.persistence.room.Query
import com.indexer.ccoin.model.Coin
import android.arch.paging.LivePagedListProvider
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy


/**
 * Created by indexer on 12/11/17.
 */
@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCoin(products: List<Coin>)

    @Query("SELECT * FROM coin ORDER BY Id ASC")
    fun getAllCoinList(): LivePagedListProvider<Int, Coin>
}