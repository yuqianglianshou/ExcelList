package com.lq.excellist

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 *
 *@author : lq
 *@date   : 2020/12/28
 *@desc   : 拦截处理事件，事件不向子控件传递
 *  不响应内部的子控件事件
 */
class InterceptLinearLayout : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // true 为拦截
        return true
    }


}