package com.indexer.ccoin

import android.app.Application
import javax.inject.Inject

/**
 * Created by indexer on 19/11/17.
 */
class CcoinApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }


}