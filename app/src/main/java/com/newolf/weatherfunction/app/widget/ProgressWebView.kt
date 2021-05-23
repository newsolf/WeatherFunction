package com.newolf.weatherfunction.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.ProgressBar
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
class ProgressWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.webViewStyle
) : WebView(context, attrs, defStyleAttr) {

    private val mProgressBar: ProgressBar = ProgressBar(
        context,
        attrs,
        android.R.attr.progressBarStyleHorizontal
    )

    init {
        val layoutParams = FrameLayout.LayoutParams(-1, 8)
        this.mProgressBar.layoutParams = layoutParams
        val drawable = context.resources.getDrawable(R.drawable.web_progress_bar_states)
        this.mProgressBar.progressDrawable = drawable
        this.addView(this.mProgressBar)
//        this.webChromeClient = WebChromeClient()
    }


    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        val lp = mProgressBar.layoutParams as LayoutParams
        lp.x = l
        lp.y = t
        mProgressBar.layoutParams = lp
        super.onScrollChanged(l, t, oldl, oldt)
    }

    inner class WebChromeClient : android.webkit.WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress == 100) {
                this@ProgressWebView.visibility = View.GONE
            } else {
                if (this@ProgressWebView.mProgressBar.visibility == View.GONE) {
                    this@ProgressWebView.mProgressBar.visibility = View.VISIBLE
                    this@ProgressWebView.setBackgroundResource(R.mipmap.bg_monkey)
                }
                this@ProgressWebView.mProgressBar.progress = newProgress
            }
            super.onProgressChanged(view, newProgress)
        }
    }
}