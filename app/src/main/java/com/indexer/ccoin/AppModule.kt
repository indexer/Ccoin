package com.indexer.ccoin

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by indexer on 19/11/17.
 * App Module create for Application
 */
@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideApplication() = app
}