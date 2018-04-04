package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.EvaluateItemBean

/**
 * 评估页面VPadapter
 * Created by guanjianhui on 18-3-21.
 */
class EvaluateAdapter(var itemList: List<EvaluateItemBean>?, var context: Context) : PagerAdapter() {


    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return itemList?.size ?: 0
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {

        var view = LayoutInflater.from(context).inflate(R.layout.layout_evaluate_item, container, false)

        var rec_evaluate = view.findViewById<RecyclerView>(R.id.rec_evaluate)
        var recAdapter = EvaluateListAdapter(itemList!![position].itemList, context)

        rec_evaluate.layoutManager = LinearLayoutManager(context)
        rec_evaluate.adapter = recAdapter

        container!!.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(`object` as View)

    }

}