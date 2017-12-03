package com.indexer.ccoin.view.dashboard.viewholder

import android.view.View
import com.indexer.ccoin.R
import com.indexer.ccoin.base.BaseViewHolder
import com.indexer.ccoin.model.Coin
import com.indexer.ccoin.utils.picasso
import kotlinx.android.synthetic.main.coin_item.view.*


/**
 * Created by indexer on 3/12/17.
 */
class CoinViewHolder(itemView: View, listener: com.indexer.ccoin.view.dashboard.listener.OnItemClickListener)
    : BaseViewHolder(itemView, listener) {
    lateinit var coin: Coin

    override fun onClick(v: View) {
        listener.onItemClick(adapterPosition)
    }

    fun onBind(coin: Coin?) {
        this.coin = coin!!
        itemView.coin_name.text = "Coin Name : " + coin?.coinName
        itemView.coin_algo.text = "Coin Alogrithm : " + coin?.algorithm
        itemView.context.picasso.load("https://www.cryptoco" +
                "mpare.com" + coin?.imageUrl).placeholder(R.drawable.place_holder)
                .resize(150, 150)
                .centerCrop().into(itemView.coin_image)
        itemView.coin_total_supply.text = "Coin Total Supply : " + coin?.totalCoinSupply
    }
}

