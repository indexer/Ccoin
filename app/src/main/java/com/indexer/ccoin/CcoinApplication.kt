package com.indexer.ccoin

import android.app.Application

/**
 * Created by indexer on 19/11/17.
 */
class CcoinApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }


    fun getAppComponent(): AppComponent {
        return appComponent
    }


}