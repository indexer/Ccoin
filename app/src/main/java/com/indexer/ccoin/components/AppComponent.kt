package com.indexer.ccoin.components

import com.indexer.ccoin.module.RoomModule
import com.indexer.ccoin.view.dashboard.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by indexer on 19/11/17.
 * AppCompnent is will inject
 */
@Singleton
@Component(modules = [(RoomModule::class)])
interface AppComponent {
    fun inject(app: MainActivity)
}