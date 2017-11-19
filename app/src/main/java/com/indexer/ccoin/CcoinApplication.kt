package com.indexer.ccoin

import android.app.Application
import com.indexer.ccoin.components.AppComponent
import com.indexer.ccoin.components.DaggerAppComponent
import com.indexer.ccoin.module.AppModule
import com.indexer.ccoin.module.RoomModule
import dagger.Module

/**
 * Created by indexer on 19/11/17.
 */
class CcoinApplication : Application() {

    private lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().
                appModule(AppModule(this))
                .roomModule(RoomModule(this)).build()
    }


    fun getAppComponent(): AppComponent {
        return appComponent
    }


}