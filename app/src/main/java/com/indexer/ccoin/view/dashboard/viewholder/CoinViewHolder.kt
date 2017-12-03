package com.indexer.ccoin.view.dashboard.viewholder

import android.view.View
import com.indexer.ccoin.base.BaseViewHolder
import com.indexer.ccoin.model.Coin
import kotlinx.android.synthetic.main.coin_item.view.*


/**
 * Created by indexer on 3/12/17.
 */
class CoinViewHolder(itemView: View, listener: OnItemClickListener?)
    : BaseViewHolder(itemView, listener) {


    override fun onClick(v: View) {

    }

    fun onBind(coin: Coin, position: Int) {
        itemView.coin_name.text = coin.coinId
    }
}