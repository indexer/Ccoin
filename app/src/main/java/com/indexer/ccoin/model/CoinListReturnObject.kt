package com.indexer.ccoin.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * Created by indexer on 12/11/17.
 */
class CoinListReturnObject {
    @SerializedName("Response")
    var response: String? = ""
    @SerializedName("Data")
    var data: HashMap<String, Coin>? = null

}