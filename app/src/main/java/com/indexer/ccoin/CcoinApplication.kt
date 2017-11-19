package com.indexer.ccoin

import android.app.Application

/**
 * Created by indexer on 19/11/17.
 */
class CcoinApplication : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent?.inject(this)
    }


}