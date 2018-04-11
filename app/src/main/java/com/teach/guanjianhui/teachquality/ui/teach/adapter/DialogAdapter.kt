package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.TeaSituationDetailBean

/**
 * Created by guanjianhui on 18-4-11.
 */
class DialogAdapter(val ctx: Context, val myList: List<TeaSituationDetailBean>) : PagerAdapter() {
    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return myList.size
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_tea_eva, null)
        val tv_eva_title = view.findViewById<TextView>(R.id.tv_eva_title)
        val tv_eva_per = view.findViewById<TextView>(R.id.tv_eva_per)
        val tv_tea_attitude_score = view.findViewById<TextView>(R.id.tv_tea_attitude_score)
        val tv_tea_content_score = view.findViewById<TextView>(R.id.tv_tea_content_score)
        val tv_tea_method_score = view.findViewById<TextView>(R.id.tv_tea_method_score)
        val tv_tea_effect_score = view.findViewById<TextView>(R.id.tv_tea_effect_score)
        val tv_tea_order_score = view.findViewById<TextView>(R.id.tv_tea_order_score)
        val tv_tea_total_score = view.findViewById<TextView>(R.id.tv_tea_total_score)

        tv_eva_title.text = myList[position].evaTitle
        tv_eva_per.text = myList[position].evaPer
        tv_tea_attitude_score.text = myList[position].attitudeScore.toString()
        tv_tea_content_score.text = myList[position].contentScore.toString()
        tv_tea_method_score.text = myList[position].methodScore.toString()
        tv_tea_effect_score.text = myList[position].effectScore.toString()
        tv_tea_order_score.text = myList[position].orderScore.toString()
        tv_tea_total_score.text = myList[position].totalScore.toString()

        container?.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as View)
    }

}