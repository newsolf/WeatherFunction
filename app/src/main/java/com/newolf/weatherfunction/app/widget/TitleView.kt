package com.newolf.weatherfunction.app.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.newolf.weatherfunction.R
import com.newolf.weatherfunction.app.utils.ResUtils
import kotlinx.android.synthetic.main.layout_title_view.view.*
import kotlin.properties.Delegates


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
class TitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var defaultTitleTextSize = 7// 默认文字大小
    private val defaultTitleBackgroundColorId = R.color.default_title_background_color// 默认背景颜色
    private val defaultTitleTextColorId = R.color.default_title_text_color// 默认文字颜色
    private val defaultTitleLineColorId = R.color.default_title_line_color// 默认底部线条颜色

    private lateinit var title: String
    private var titleTextSize by Delegates.notNull<Int>()
    private var titleTextColor by Delegates.notNull<Int>()
    private var titleBackgroundColor by Delegates.notNull<Int>()
    private var titleLineColor by Delegates.notNull<Int>()

    init {
        initAttrs(context, attrs)
        initView()
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val dm = resources.displayMetrics
        this.defaultTitleTextSize =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                defaultTitleTextSize.toFloat(),
                dm
            ).toInt()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView)
        title = typedArray.getString(R.styleable.TitleView_titleViewText)
        titleTextSize = typedArray.getDimensionPixelSize(
            R.styleable.TitleView_titleViewTextSize,
            defaultTitleTextSize
        )
        titleTextColor = typedArray.getColor(
            R.styleable.TitleView_titleViewTextColor,
            ResUtils.getColor(defaultTitleTextColorId)
        )
        titleBackgroundColor = typedArray.getColor(
            R.styleable.TitleView_titleViewBackground,
            ResUtils.getColor(defaultTitleBackgroundColorId)
        )
        titleLineColor = typedArray.getColor(
            R.styleable.TitleView_titleViewLineColor,
            ResUtils.getColor(defaultTitleLineColorId)
        )
        typedArray.recycle()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.layout_title_view, this, true)
        this.orientation = LinearLayout.VERTICAL
        this.setBackgroundColor(titleBackgroundColor)

        tvTitleView.text = title
        tvTitleView.setTextColor(titleTextColor)

        titleLineView.setBackgroundColor(titleLineColor)
    }
}