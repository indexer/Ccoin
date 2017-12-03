package com.indexer.ccoin.view.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.indexer.ccoin.R
import com.indexer.ccoin.model.Coin
import com.indexer.ccoin.view.dashboard.viewholder.CoinViewHolder


/**
 * Created by indexer on 3/12/17.
 */
class CoinListAdapter : com.indexer.ccoin.base.BaseAdapter<CoinViewHolder, Coin>(Coin.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent,
                false)
        return CoinViewHolder(view, null)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        holder.onBind(coin)
    }
}