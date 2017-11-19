package com.indexer.ccoin

import dagger.Provides
import javax.inject.Singleton
import android.app.Application
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.database.CoinDao
import dagger.Module


/**
 * Created by indexer on 19/11/17.
 */
@Module
class RoomModule(mApplication: Application) {

    private val appDatabase: AppDatabase = AppDatabase.getDatabase(mApplication)

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }

    @Singleton
    @Provides
    internal fun providesProductDao(demoDatabase: AppDatabase): CoinDao {
        return demoDatabase.coinDao
    }


}