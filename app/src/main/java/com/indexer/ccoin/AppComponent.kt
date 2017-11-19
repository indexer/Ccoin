package com.indexer.ccoin

import android.content.Context
import dagger.Component
import javax.inject.Singleton

/**
 * Created by indexer on 19/11/17.
 * AppCompnent is will inject
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: Context)
}