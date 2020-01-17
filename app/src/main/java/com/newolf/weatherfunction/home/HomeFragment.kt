package com.newolf.weatherfunction.home

import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.base.BaseFragment
import com.newolf.weatherfunction.app.bean.TodayDetail
import com.newolf.weatherfunction.model.DetailBean
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun bindLayout(): Int {
        return R.layout.home_fragment
    }

    private lateinit var detailAdapter :DetailAdapter

    override fun initView() {
        rvDetail.isNestedScrollingEnabled = false
        rvDetail.layoutManager = GridLayoutManager(mContext,3)
        detailAdapter =  DetailAdapter(null)
        rvDetail.adapter = detailAdapter
    }

    override fun loadData() {

    }

    override fun initListener() {

    }

    fun updateCity(cityCode: String) {
        LogUtils.e("cityCode =  $cityCode")

    }

    fun updateTodayDetail(detailBean: DetailBean) {
        val detailList: MutableList<TodayDetail> = ArrayList()
        detailBean.apply {
            detailList.add(
                TodayDetail(
                    R.mipmap.ic_launcher,
                    R.string.detail_key_feelst,
                    this.feelst + "°C"
                )
            )
            detailList.add(
                TodayDetail(
                    R.mipmap.ic_launcher,
                    R.string.detail_key_humidity,
                    this.humidity + "%"
                )
            )
//            detailList.add(
//                TodayDetail(
//                    R.mipmap.ic_launcher,
//                    R.string.detail_key_feel,
//                    this.w + "°C"
//                )
//            )
//            detailList.add(
//                TodayDetail(
//                    R.mipmap.ic_launcher,
//                    R.string.detail_key_feel,
//                    this.feelst + "°C"
//                )
//            )
//            detailList.add(
//                TodayDetail(
//                    R.mipmap.ic_launcher,
//                    R.string.detail_key_feel,
//                    this.feelst + "°C"
//                )
//            )
//            detailList.add(
//                TodayDetail(
//                    R.mipmap.ic_launcher,
//                    R.string.detail_key_feel,
//                    this.feelst + "°C"
//                )
//            )
        }

        detailAdapter.setNewData(detailList)

    }


}
