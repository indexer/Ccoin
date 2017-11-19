package com.zeta.dashboard.api

import com.indexer.ccoin.api.Config
import com.indexer.ccoin.model.CoinListReturnObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(Config.coinList)
    fun getCoinList(): Call<CoinListReturnObject>
}