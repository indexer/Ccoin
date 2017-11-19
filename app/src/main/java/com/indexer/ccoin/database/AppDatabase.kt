package com.indexer.ccoin.database

/**
 * Created by indexer on 26/7/17.
 */

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.indexer.ccoin.model.Coin


@Database(entities = arrayOf(Coin::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    val isDatabaseCreated: LiveData<Boolean> get() = mIsDatabaseCreated

    abstract val coinDao: CoinDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        private val mIsDatabaseCreated = MutableLiveData<Boolean>()

        fun getDatabase(context: Context): AppDatabase {
            mIsDatabaseCreated.value = false// Trigger an update to show a loading screen.
            when (INSTANCE) {
                null -> {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,
                            "product_db")
                            .build()
                    mIsDatabaseCreated.value = true
                }
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}