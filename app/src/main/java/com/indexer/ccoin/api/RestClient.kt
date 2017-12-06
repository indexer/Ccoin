package com.indexer.ccoin.api

import com.indexer.ccoin.R
import com.zeta.dashboard.api.ApiService
import okhttp3.*
import okhttp3.internal.Internal.instance
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RestClient private constructor() {
    private val mService: ApiService

    init {
        val restAdapter = Retrofit.Builder().baseUrl(Config.base).
                addConverterFactory(GsonConverterFactory.create()).client(getNewHttpClient()).build()
        mService = restAdapter.create(ApiService::class.java)
    }

    private fun getNewHttpClient(): OkHttpClient {
        val defaultTimeout = R.integer.default_time_out.toLong()
        val connectTimeout = R.integer.connect_time_out.toLong()
        val builder = OkHttpClient.Builder().followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .cache(null).apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(Level.NONE))
            connectTimeout(connectTimeout, TimeUnit.SECONDS)
            followRedirects(true)
            followSslRedirects(true)
            writeTimeout(defaultTimeout, TimeUnit.SECONDS)
            readTimeout(defaultTimeout, TimeUnit.SECONDS)
            connectTimeout(defaultTimeout, TimeUnit.SECONDS)
                    .writeTimeout(defaultTimeout, TimeUnit.SECONDS)
                    .readTimeout(defaultTimeout, TimeUnit.SECONDS)
        }
        return builder.build()
    }


    companion object {
        @Synchronized
        fun getService(): ApiService {
            return getInstance().mService
        }

        private fun getInstance(): RestClient {
            return instance as RestClient
        }
    }
}

