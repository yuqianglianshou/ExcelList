package com.lq.excellist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv.view.*

/**
 *
 *@author : lq
 *@date   : 2020/12/29
 *@desc   :
 *
 */
class MyAdapter(
    private val context: Context,
    private val resource: Int,
    private val headScrollView: CanObserverHorizontalScrollView
) : RecyclerView.Adapter<MyAdapter.MyViewHoder>() {
    //数据集合
    private var mList = ArrayList<DataBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHoder {
        return MyViewHoder(LayoutInflater.from(parent.context).inflate(resource, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHoder, position: Int) {

        val itemView = holder.itemView
        val bean = mList.get(position)

        //赋值
        itemView.tv_name.text = bean.name
        itemView.tv_a.text = bean.data1
        itemView.tv_b.text = bean.data2
        itemView.tv_c.text = bean.data3
        itemView.tv_d.text = bean.data4
        itemView.tv_e.text = bean.data5
        itemView.tv_f.text = bean.data6
        itemView.tv_g.text = bean.data7

        //item 第一个单元格可点击
        itemView.tv_name.setOnClickListener {
            Toast.makeText(context, "守得云开见月明 " + bean.name, Toast.LENGTH_SHORT).show()
        }

        //注意：放开点击事件 会有 左右滑动难以响应的问题
//        itemView.linearLayout.setOnClickListener {
//            Toast.makeText(context,"守得云开见月明 linearLayout",Toast.LENGTH_SHORT).show()
//        }

        //解决 监听 无限增多问题
        if (itemView.tag == null) {
            //将rv item 中的scrollview 添加为 头布局 中 scrollview 的观察者，使每一个item的滚动跟随 头部 的滚动
            val listener = OnScrollChangedListenerImp(itemView.horizontalScrollView)
            headScrollView.addScrollChangedListener(listener)

            itemView.tag = listener
        }


    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun setData(list: List<DataBean>) {
        mList.clear()
        mList = list as ArrayList<DataBean>
        notifyDataSetChanged()
    }

    class MyViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**
     * 滚动监听  的 实现
     */
    internal class OnScrollChangedListenerImp(var mScrollViewArg: HorizontalScrollView) :
        CanObserverHorizontalScrollView.ScrollViewObserver.OnScrollChangedListener {

        override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
            mScrollViewArg.smoothScrollTo(l, t)
        }
    }

}