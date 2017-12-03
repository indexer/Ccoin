package com.indexer.ccoin.base

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.view.ViewGroup


abstract class BaseAdapter<VH : BaseViewHolder, T>(diffCallback: DiffCallback<T>) : PagedListAdapter<T, VH>(diffCallback) {


    /**
     * @param items new array list for adapter
     */


    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    abstract override fun onBindViewHolder(holder: VH, position: Int)


}
