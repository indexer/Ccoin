package com.indexer.ccoin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import com.indexer.ccoin.database.AppDatabase
import com.indexer.ccoin.model.Coin
import com.indexer.ccoin.view.dashboard.MainActivity
import com.indexer.ccoin.view.dashboard.viewmodel.CoinListViewModel
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.junit.Assert.*
import org.junit.Before
import org.robolectric.Robolectric
import io.reactivex.internal.disposables.DisposableHelper.dispose
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.robolectric.RuntimeEnvironment


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(RobolectricTestRunner::class)
class DatabaseUnitTest {

    private var mAppDatabase: AppDatabase? = null
    @Before
    fun setUpDatabase() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        mAppDatabase = AppDatabase.getDatabase(activity)
    }

    @Test
    @Throws(Exception::class)
    fun dataBaseWasCorrect() {
        assertNotEquals(null, mAppDatabase)
    }

    @Test
    @Throws(Exception::class)
    fun daoSelectWasCorrect() {
        val coinList = Observable.just(mAppDatabase).subscribeOn(Schedulers.io()).map { it ->
            it.coinDao.getAllCoin()
        }.blockingFirst() as ArrayList<Coin>
        assertNotEquals(0, coinList)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mAppDatabase?.close()
    }
}