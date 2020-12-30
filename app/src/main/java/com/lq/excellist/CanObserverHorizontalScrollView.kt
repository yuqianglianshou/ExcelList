package com.lq.excellist

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.HorizontalScrollView

/**
 *
 *@author : lq
 *@date   : 2020/12/28
 *@desc   : 可添加观察者的 HorizontalScrollView
 *
 */
private const val TAG = "lq"
class CanObserverHorizontalScrollView : HorizontalScrollView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    //定义观察者 集合
    var mScrollViewObserver = ScrollViewObserver()

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        /*
		 * 当自己 滚动条移动后，引发 滚动事件。通知给所有观察者。
		 */
        if (mScrollViewObserver != null) {
            mScrollViewObserver.notifyOnScrollChanged(l, t, oldl, oldt)
        }

    }

    /*
     * 订阅 本控件 的 滚动条变化事件
     * */
    fun addScrollChangedListener(listener: ScrollViewObserver.OnScrollChangedListener) {
        mScrollViewObserver.addScrollChangedListener(listener)
    }

    /*
     * 取消 订阅 本控件 的 滚动条变化事件
     * */
    fun removeScrollChangedListener(listener: ScrollViewObserver.OnScrollChangedListener) {
        mScrollViewObserver.removeScrollChangedListener(listener)
    }


    /**
     *
     *@author : lq
     *@date   : 2020/12/29
     *@desc   : scrollview  的观察者
     *
     */
    class ScrollViewObserver {
        var mList: ArrayList<OnScrollChangedListener> = ArrayList()

        //添加一个监听者
        fun addScrollChangedListener(listener: OnScrollChangedListener) {
//            Log.i(TAG, "addScrollChangedListener: "+listener.hashCode())
            mList.add(listener)
        }

        //移除一个监听者
        fun removeScrollChangedListener(listener: OnScrollChangedListener) {
//            Log.i(TAG, "removeScrollChangedListener: "+listener.hashCode())
            mList.remove(listener)
        }

        //滚动 传递给所有 监听者
        fun notifyOnScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
            if (mList == null || mList.size == 0) {
                return
            }
            var iterator = mList.iterator()
            while (iterator.hasNext()) {
                var listener = iterator.next()
                if (listener != null) {
                    listener.onScrollChanged(l, t, oldl, oldt)
                } else {
                    iterator.remove()
                }
            }

//            for (i in mList.indices) {
//                if (mList.get(i) != null) {
//                    mList.get(i).onScrollChanged(l, t, oldl, oldt)
//                }
//            }

            Log.i("lq", "notifyOnScrollChanged: mList.size == " + mList.size)
        }


        /**
         *
         *@author : lq
         *@date   : 2020/12/29
         *@desc   : 当发生了滚动事件时
         *
         */
        interface OnScrollChangedListener {
            fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int)
        }


    }

}