package com.indexer.ccoin.components

import android.content.Context
import com.indexer.ccoin.module.AppModule
import com.indexer.ccoin.module.RoomModule
import com.indexer.ccoin.view.dashboard.dashboard.MainActivity
import com.indexer.ccoin.view.dashboard.detail.DetailActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by indexer on 19/11/17.
 * AppCompnent is will inject
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class))
interface AppComponent {
    fun inject(app: MainActivity)
    fun inject(app: DetailActivity)
    fun inject(app: Context)
}