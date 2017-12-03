package com.indexer.ccoin.base

import android.support.v7.widget.RecyclerView
import android.view.View


abstract class BaseViewHolder(itemView: View, protected val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        this.itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        listener?.onItemClick(adapterPosition)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
