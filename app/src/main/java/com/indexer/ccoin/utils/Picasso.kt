package com.indexer.ccoin.utils

import android.content.Context
import com.squareup.picasso.Picasso

/**
 * Created by indexer on 12/11/17.
 */
val Context.picasso: Picasso
    get() = Picasso.with(this)