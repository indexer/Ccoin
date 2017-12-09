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
import android.graphics.drawable.BitmapDrawable


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
        itemView.coin_name.text = itemView.resources.getString(R.string.coin_name)
                .plus(other = coin.coinName)
        itemView.coin_algo.text = itemView.resources.
                getString(R.string.coin_algorithm).plus(other = coin.algorithm)
        itemView.context.picasso.load(itemView.resources.getString(R.string.default_url)
                .plus(other = coin.imageUrl)).into(itemView.coin_image)
        /*val bitmap = (itemView.coin_image.drawable as BitmapDrawable)
        itemView.coin_image.background = bitmap*/

        itemView.coin_total_supply.text = coin.totalCoinSupply
        itemView.coin_name.typeface = Typer.set(itemView.context).getFont(Font.ROBOTO_CONDENSED_BOLD)
        itemView.coin_algo.typeface = Typer.set(itemView.context).getFont(Font.ROBOTO_MEDIUM)
        itemView.coin_total_supply.typeface = Typer.set(itemView.context).getFont(Font.ROBOTO_BLACK)

    }

}

