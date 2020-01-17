package com.newolf.weatherfunction.app.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2018/1/12
 * 描述:
 * 历史:<br/>
 * ================================================
 */
abstract class BaseFragment:Fragment(),IBaseView {
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = inflater.context
        return inflater.inflate(bindLayout(),container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtras()
        initView()
        loadData()
        initListener()
    }

    abstract fun bindLayout(): Int

    override fun getExtras() {

    }


}