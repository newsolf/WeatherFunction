package com.newolf.weatherfunction.app.base

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
interface IBaseView {
    fun getExtras()
    fun initView()
    fun loadData()
    fun initListener()

}