package com.newolf.weatherfunction.app.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import com.newolf.weatherfunction.R

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2021-05-22
 * 描述:
 * 历史:<br/>
 * ================================================
 */
class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    lateinit var mTvMainTitleLeft: TextView
    lateinit var mTvMainTitle: TextView
    lateinit var mTvMainTitleRight: TextView


    override fun onFinishInflate() {
        super.onFinishInflate()
        mTvMainTitleLeft = findViewById<TextView>(R.id.lt_main_title_left)
        mTvMainTitle = findViewById<TextView>(R.id.lt_main_title)
        mTvMainTitleRight = findViewById<TextView>(R.id.lt_main_title_right)
    }

    /**
     * 设置主title的内容
     */
    fun setMainTitle(text: String?) {
        this.title = null
        mTvMainTitle.visibility = VISIBLE
        mTvMainTitle.text = text
    }

    /**
     * 设置主title的内容文字的颜色
     */
    fun setMainTitleColor(color: Int) {
        mTvMainTitle.setTextColor(color)
    }

    /**
     * 设置title左边文字
     */
    fun setMainTitleLeftText(text: String?) {
        mTvMainTitleLeft.visibility = VISIBLE
        mTvMainTitleLeft.text = text
    }

    /**
     * 设置title左边文字颜色
     */
    fun setMainTitleLeftColor(color: Int) {
        mTvMainTitleLeft.setTextColor(color)
    }

    /**
     * 设置title左边图标
     */
    fun setMainTitleLeftDrawable(res: Int) {
        val dwLeft: Drawable? = ContextCompat.getDrawable(context, res)
        dwLeft?.setBounds(0, 0, dwLeft.minimumWidth, dwLeft.minimumHeight)
        mTvMainTitleLeft.setCompoundDrawables(dwLeft, null, null, null)
    }

    /**
     * 设置title右边文字
     */
    fun setMainTitleRightText(text: String?) {
        mTvMainTitleRight.visibility = VISIBLE
        mTvMainTitleRight.text = text
    }

    /**
     * 设置title右边文字颜色
     */
    fun setMainTitleRightColor(color: Int) {
        mTvMainTitleRight.setTextColor(color)
    }

    /**
     * 设置title右边图标
     */
    fun setMainTitleRightDrawable(res: Int) {
        val dwRight: Drawable? = ContextCompat.getDrawable(context, res)
        dwRight?.setBounds(0, 0, dwRight.minimumWidth, dwRight.minimumHeight)
        mTvMainTitleRight.setCompoundDrawables(null, null, dwRight, null)
    }

    /**
     * 设置toolbar状态栏颜色
     */
    fun setToolbarBackground(res: Int) {
        setBackgroundResource(res)
    }

    /**
     * 设置toolbar左边图标
     */
    fun setToolbarLeftBackImageRes(res: Int) {
        this.setNavigationIcon(res)
    }

    /**
     * 设置toolbar左边图标是否可见
     */
    fun setToolbarLeftBackImageVisibly(visibly: Boolean) {
        if (!visibly) {
            this.navigationIcon = null
        }
    }

    /**
     * 设置toolbar左边文字
     */
    fun setToolbarLeftText(text: String?) {
        this.navigationContentDescription = text
    }

    /**
     * 设置toolbar的标题
     */
    fun setToolbarTitle(text: String?) {
        this.title = text
    }

    /**
     * 设置toolbar标题的颜色
     */
    fun setToolbarTitleColor(color: Int) {
        setTitleTextColor(color)
    }

    /**
     * 设置toolbar子标题
     */
    fun setToolbarSubTitleText(text: String?) {
        this.subtitle = text
    }

    /**
     * 设置toolbar子标题颜色
     */
    fun setToolbarSubTitleTextColor(color: Int) {
        setSubtitleTextColor(color)
    }


}