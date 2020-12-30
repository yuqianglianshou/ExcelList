package com.lq.excellist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_rv_header.*
import java.util.*


/**
 * 原理：
 * 将rv的touch事件交给表头的scrollview处理并监听表头的scrollview，表头的scrollview滚动（滑动表头的scrollview 或 滑动rv），通知监听者（rv中所有的scrollview）一起滚动。
 *
 * 1，表头 和 内容 作为两个部分 单独处理。
 * 2，将整个 recyclerview 的touch事件交给表头的 scrollview 处理。
 * 3，将recyclerview的每个item中的scrollview添加为头部scrollview的观察者，使之跟随头部scrollview一起滚动。
 *
 * 表头布局 item_rv_header 和 rv 的 item 布局 item_rv 区别，item_rv 布局在 horizontalscrollview 外 包裹了一层拦截事件的LinearLayout,
 * 即InterceptLinearLayout，用于拦截每个item中scrollview的滚动事件。否则会看到item中每个scrollview都可以单独滚动。item_rv_header 中的 horizontalscrollview 使用的是自定义的可以添加观察者的
 * horizontalscrollview，用于通知所有观察者一起滑动。
 *
 *
 */
class MainActivity : AppCompatActivity() {


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv.setOnTouchListener { v, event ->
            //当在rv 上touch时，将事件分发给 表头的 scrollview 处理
            headerHorizontalScrollView.onTouchEvent(event)
            //如果删掉下面这行代码，rv的上下滚动效果会失效
            onTouchEvent(event)
        }

//        设置布局管理器
        rv.layoutManager = LinearLayoutManager(this)

        val myAdapter = MyAdapter(this, R.layout.item_rv, headerHorizontalScrollView)

        rv.adapter = myAdapter

//        设置数据
        myAdapter.setData(getData())


    }

    /**
     * 模拟数据
     */
    private fun getData(): List<DataBean> {
        val list = ArrayList<DataBean>()
        for (i in 0..119) {
            list.add(
                DataBean(
                    "name $i",
                    "A_ $i", "B_ $i", "C_ $i", "D_ $i", "E_ $i", "F_ $i", "G_ $i"
                )
            )
        }
        return list
    }

}