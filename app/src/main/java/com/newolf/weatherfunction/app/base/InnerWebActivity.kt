package com.newolf.weatherfunction.app.base


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewStub
import android.webkit.*
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.widget.CustomToolbar
import kotlinx.android.synthetic.main.activity_inner_web.*


class InnerWebActivity : BaseActivity() {
    companion object {
        const val STRING_EXTRA_URL = "STRING_EXTRA_URL"
    }

    lateinit var mUrl: String
    private lateinit var mWebView: WebView


    override fun getExtras() {
        mUrl = intent.getStringExtra(STRING_EXTRA_URL)
    }

    override fun bindLayout(): Int {
        return R.layout.activity_inner_web
    }

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mWebView = findViewById(R.id.web_view)
        mWebView.setBackgroundResource(R.mipmap.bg_monkey)
        initWebView()
    }

    override fun initListener() {
        refreshLayout.setOnRefreshListener{
            mWebView.setBackgroundResource(R.mipmap.bg_monkey)
            mWebView.loadUrl(mUrl)
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView() {
        val settings: WebSettings = mWebView.getSettings()
        if (NetworkUtils.isConnected()) {
            settings.cacheMode = WebSettings.LOAD_DEFAULT
        } else {
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        mWebView.loadUrl(mUrl)

        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        //自适应屏幕
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN;
        settings.loadWithOverviewMode = true;
        setUpWebClient()
    }

    private fun setUpWebClient() {
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                LogUtils.e("url = $url")
                view.loadUrl(url)
                return false
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                super.onReceivedError(view, request, error)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    loadWebError(error.toString())
                } else {
                    loadWebError(error.description.toString())
                }
            }
        }
        val wvcc: WebChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String?) {
                super.onReceivedTitle(view, title)
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100){
                    refreshLayout.finishRefresh()
                }
            }
        }
        mWebView.webChromeClient = wvcc
    }

    fun loadWebError(error: String) {
        mWebView.visibility = View.GONE
        findViewById<View>(R.id.vs_net_error).visibility = View.VISIBLE
    }


    override fun onResume() {
        super.onResume()
        mWebView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mWebView.onPause()
    }


}