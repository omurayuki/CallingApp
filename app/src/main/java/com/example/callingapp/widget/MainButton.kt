package com.example.callingapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.use
import com.example.callingapp.R
import kotlinx.android.synthetic.main.main_button.view.*

class MainButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.main_button, this, true)

        context.obtainStyledAttributes(attrs, R.styleable.MainButton, 0, 0).use {
            title_en.text = it.getString(R.styleable.MainButton_title_en)
            title_jp.text = it.getString(R.styleable.MainButton_title_jp)
        }
    }
}