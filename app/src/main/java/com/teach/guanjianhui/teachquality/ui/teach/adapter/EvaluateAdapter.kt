package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
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
        return itemList?.size?:0
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        return super.instantiateItem(container, position)
        var view = LayoutInflater.from(context).inflate(R.layout.layout_evaluate_item, container, false)
        container!!.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        super.destroyItem(container, position, `object`)

    }

}