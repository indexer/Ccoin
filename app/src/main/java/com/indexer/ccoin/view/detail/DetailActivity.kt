package com.indexer.ccoin.view.dashboard.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.indexer.ccoin.R
import com.indexer.ccoin.database.AppDatabase


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppDatabase.destroyInstance()
    }
}




