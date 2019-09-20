package com.newolf.weatherfunction.app.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.blankj.utilcode.util.LogUtils
import com.newolf.weatherfunction.R








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
class IndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var mPaint: Paint
    private var textSize = 6
    private var intervalValue = 0
    private var markerId = 0
    private var indicatorValue = 0
    private var textColor = 0
    private var paddingTopInXML = 0
    private var indicatorStringsResourceId = R.array.indicator_strings
    private var textColorId = R.color.indicator_text_color// 默认文字颜色
    private var indicatorColorsResourceId = R.array.indicator_colors
    private lateinit var marker : Bitmap
    private lateinit var indicatorStrings: Array<String>
    private lateinit var indicatorColorIds: IntArray
    private var indicatorViewWidth = 0

    init {
        initView(context, attrs)
    }
    private fun initView(context: Context, attrs: AttributeSet?) {
        this.orientation = LinearLayout.HORIZONTAL
        this.isDrawingCacheEnabled = true

        initPaint()
        initAttrs(attrs)
        fillViewToParent(context)

        this.setWillNotDraw(false);// 确保onDraw()被调用

        this.paddingTopInXML = this.paddingTop
        this.setPadding(this.paddingLeft + this.marker.getWidth() / 2,
            this.paddingTop + this.marker.height,
            this.paddingRight + this.marker.width / 2,
            this.paddingBottom
        );

    }

    private fun initPaint() {
        mPaint = Paint()
        // 设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        mPaint.isAntiAlias = true
        // 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mPaint.isDither = true
    }

    @SuppressLint("ResourceAsColor")
    private fun initAttrs(attrs: AttributeSet?) {
        val dm = resources.displayMetrics
        this.textSize =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat(), dm).toInt()
        this.intervalValue =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                intervalValue.toFloat(), dm
            ).toInt()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView)
        this.markerId = typedArray.getResourceId(
            R.styleable.IndicatorView_marker,
            R.drawable.ic_vector_indicator_down
        )
        this.marker = drawableToBitmap(createVectorDrawable(markerId, R.color.indicator_color_1))
        this.indicatorValue =
            typedArray.getInt(R.styleable.IndicatorView_indicatorValue, indicatorValue)
        this.textSize =
            typedArray.getDimensionPixelSize(R.styleable.IndicatorView_textSize, textSize)
        this.intervalValue =
            typedArray.getDimensionPixelSize(R.styleable.IndicatorView_intervalSize, intervalValue)
        this.textColor = typedArray.getColor(
            R.styleable.IndicatorView_textColor,
            textColorId
        )
        this.indicatorStringsResourceId = typedArray.getInt(
            R.styleable.IndicatorView_indicatorStrings,
            indicatorStringsResourceId
        )
        this.indicatorColorsResourceId =
            typedArray.getInt(R.styleable.IndicatorView_indicatorColors, indicatorColorsResourceId)
        LogUtils.e("indicatorStringsResourceId $indicatorStringsResourceId ,indicatorColorsResourceId $indicatorColorsResourceId")
        typedArray.recycle()
    }

    private fun fillViewToParent(context: Context) {
        indicatorStrings = context.resources.getStringArray(indicatorStringsResourceId)
        indicatorColorIds = context.resources.getIntArray(indicatorColorsResourceId)
        LogUtils.e("indicatorStrings = ${indicatorStrings.size} ,indicatorColorIds = ${indicatorColorIds.size}")
        require(indicatorStrings.size == indicatorColorIds.size) { "qualities和aqiColors的数组长度不一致！" }
        for (i in indicatorStrings.indices) {
            addTextView(context, indicatorStrings[i], indicatorColorIds[i])
            if (i != indicatorStrings.size - 1) {
                addBlankView(context)
            }
        }
    }

    private fun addTextView(context: Context, text: String, color: Int) {
        val textView = TextView(context)
        textView.setBackgroundColor(color)
        textView.text = text
        textView.setTextColor(textColor)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
        textView.setSingleLine()
        textView.gravity = Gravity.CENTER
        textView.layoutParams =
            LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f)
        this.addView(textView)
    }

    private fun addBlankView(context: Context) {
        val transparentView = View(context)
        transparentView.setBackgroundColor(Color.TRANSPARENT)
        transparentView.layoutParams = LayoutParams(
            intervalValue,LayoutParams.WRAP_CONTENT
        )
        this.addView(transparentView)
    }


    private fun createVectorDrawable(drawableId: Int, @ColorInt colorInt: Int): Drawable {
        val vectorDrawableCompat: VectorDrawableCompat? =
            VectorDrawableCompat.create(resources, drawableId, context.theme)
        val drawable = vectorDrawableCompat as Drawable
        DrawableCompat.setTint(drawable, colorInt)
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
        return drawable
    }

    private fun drawableToBitmap(drawable:Drawable) :Bitmap{
        if (drawable is BitmapDrawable){
            return drawable.bitmap
        }

        val bitmap :Bitmap = if (drawable.intrinsicWidth <=0 ||drawable.intrinsicHeight<= 0){
            Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888)
        }else{
            Bitmap.createBitmap(drawable.intrinsicWidth,drawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas()
        drawable.setBounds(0,0,canvas.width,canvas.height)
        drawable.draw(canvas)
        return bitmap
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        this.indicatorViewWidth = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var indicatorViewHeight = MeasureSpec.getSize(heightMeasureSpec)

        val desiredWidth = indicatorViewWidth + paddingLeft + paddingRight
        val desiredHeight = this.getChildAt(0).measuredHeight + paddingTop + paddingBottom

        //测量宽度
        when (widthMode) {
            MeasureSpec.EXACTLY -> {
            }
            MeasureSpec.AT_MOST -> indicatorViewWidth =
                Math.min(desiredWidth, indicatorViewWidth)
            MeasureSpec.UNSPECIFIED -> indicatorViewWidth = desiredWidth
        }

        //测量高度
        when (heightMode) {
            MeasureSpec.EXACTLY -> {
            }
            MeasureSpec.AT_MOST -> indicatorViewHeight =
                Math.min(desiredHeight, indicatorViewHeight)
            MeasureSpec.UNSPECIFIED -> indicatorViewHeight = desiredHeight
        }
        setMeasuredDimension(indicatorViewWidth, indicatorViewHeight)
    }


    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        drawMarkView(canvas)
    }

    private fun drawMarkView(canvas: Canvas?) {
        val width =
            this.indicatorViewWidth - this.paddingLeft - this.paddingRight - intervalValue * 5

        var left = this.paddingLeft
        left += when {
            indicatorValue <= 50 -> indicatorValue * (width * 4 / 6 / 200)
            indicatorValue in 51..100 -> indicatorValue * (width * 4 / 6 / 200) + intervalValue
            indicatorValue in 101..150 -> indicatorValue * (width * 4 / 6 / 200) + intervalValue * 2
            indicatorValue in 151..200 -> indicatorValue * (width * 4 / 6 / 200) + intervalValue * 3
            indicatorValue in 201..300 -> width * 4 / 6 + (indicatorValue - 200) * width / 6 / 100 + intervalValue * 4
            else -> width * 5 / 6 + (indicatorValue - 300) * width / 6 / 200 + intervalValue * 5
        }
        canvas?.drawBitmap(marker, (left - marker.width / 2 - 2).toFloat(), this.paddingTopInXML.toFloat(), mPaint)
    }


    private var indicatorValueChangeListener: IndicatorValueChangeListener? = null

    fun setIndicatorValueChangeListener(indicatorValueChangeListener: IndicatorValueChangeListener) {
        this.indicatorValueChangeListener = indicatorValueChangeListener
    }

    fun setIndicatorValue(indicatorValue: Int) {

        check(indicatorValue >= 0) { "参数indicatorValue必须大于0" }

        this.indicatorValue = indicatorValue
        if (indicatorValueChangeListener != null) {
            val stateDescription: String
            val indicatorTextColor: Int
            when {
                indicatorValue <= 50 -> {
                    stateDescription = indicatorStrings[0]
                    indicatorTextColor = indicatorColorIds[0]
                }
                indicatorValue in 51..100 -> {
                    stateDescription = indicatorStrings[1]
                    indicatorTextColor = indicatorColorIds[1]
                }
                indicatorValue in 101..150 -> {
                    stateDescription = indicatorStrings[2]
                    indicatorTextColor = indicatorColorIds[2]
                }
                indicatorValue in 151..200 -> {
                    stateDescription = indicatorStrings[3]
                    indicatorTextColor = indicatorColorIds[3]
                }
                indicatorValue in 201..300 -> {
                    stateDescription = indicatorStrings[4]
                    indicatorTextColor = indicatorColorIds[4]
                }
                else -> {
                    stateDescription = indicatorStrings[5]
                    indicatorTextColor = indicatorColorIds[5]
                }
            }
            marker.recycle()
            marker = drawableToBitmap(createVectorDrawable(markerId, indicatorTextColor))
            indicatorValueChangeListener!!.onChange(
                this.indicatorValue,
                stateDescription,
                indicatorTextColor
            )
        }
        invalidate()
    }

}

