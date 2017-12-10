package com.indexer.ccoin.view.dashboard.viewholder

import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.view.View
import com.elmargomez.typer.Font
import com.elmargomez.typer.Typer
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
        itemView.coin_name.text = coin.coinName
        itemView.coin_algo.text = itemView.resources.
                getString(R.string.coin_algorithm).plus(other = coin.algorithm)
        itemView.context.picasso.load(itemView.resources.getString(R.string.default_url)
                .plus(other = coin.imageUrl)).into(itemView.coin_image)

        itemView.coin_total_supply.text = coin.totalCoinSupply.plus(itemView.resources.
                getString(R.string.total))
        itemView.coin_url.text = itemView.context.resources.getString(R.string.default_url).plus(coin.Url)
        itemView.coin_concept.text = itemView.resources.getString(R.string.coin_proof).plus(coin.proofType)
        itemView.coin_concept.typeface = Typer.set(itemView.context).getFont(Font.ROBOTO_REGULAR)
        itemView.coin_name.typeface = Typer.set(itemView.context).getFont(Font.ROBOTO_CONDENSED_BOLD)
        itemView.coin_algo.typeface = Typer.set(itemView.context).getFont(Font.ROBOTO_REGULAR)
        itemView.coin_total_supply.typeface = Typer.set(itemView.context).getFont(Font.ROBOTO_REGULAR)

    }

}

