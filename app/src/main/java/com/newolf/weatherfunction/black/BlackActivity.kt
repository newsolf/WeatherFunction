package com.newolf.weatherfunction.black


import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.base.BaseActivity
import java.util.*


class BlackActivity : BaseActivity() {
    private lateinit var blackNineAdapter: BlackNineAdapter
    override fun bindLayout(): Int {
        return R.layout.activity_black
    }

    override fun initView() {
        val rvList = findViewById<RecyclerView>(R.id.rv_list)
        rvList.layoutManager = GridLayoutManager(mContext, 3)
        blackNineAdapter = BlackNineAdapter(this, null)
        rvList.adapter = blackNineAdapter
    }

    override fun loadData() {
        val data = ArrayList<String>()

        for (index in 1..9){
            data.add("$index")
        }
        blackNineAdapter.setNewInstance(data)
    }

    override fun initListener() {

    }
}