package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.EvaluateItemBean
import com.teach.guanjianhui.teachquality.beans.EvaluateStuBean
import com.teach.guanjianhui.teachquality.constant.Field

/**
 * Created by guanjianhui on 18-3-23.
 */
class EvaluateGroupAdapter(var ctx: Context, var list: List<EvaluateItemBean>?) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var myHolder: MyHolder?
        var view: View
        if (convertView == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.item_evaluate_first, null)
            myHolder = MyHolder(view)
            view.tag = myHolder
        } else {
            view = convertView
            myHolder = convertView.tag as MyHolder
        }

        when (list!![position].type) {
            Field.EVALUATE_ATTITUDE -> {
                myHolder.im_eva_first.setImageResource(R.mipmap.ic_eva_attitude)
            }
            Field.EVALUATE_CONTENT -> {
                myHolder.im_eva_first.setImageResource(R.mipmap.ic_eva_content)
            }
            Field.EVALUATE_METHOD -> {
                myHolder.im_eva_first.setImageResource(R.mipmap.ic_eva_method)
            }
            Field.EVALUATE_EFFECT -> {
                myHolder.im_eva_first.setImageResource(R.mipmap.ic_eva_effect)
            }
            Field.EVALUATE_ORDER -> {
                myHolder.im_eva_first.setImageResource(R.mipmap.ic_eva_order)
            }
        }


        myHolder.tv_eva_first.setText(list!![position].title)
        return view!!
    }

    override fun getItem(position: Int): EvaluateItemBean? {
        return list?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list?.size ?: 0
    }

    class MyHolder(view: View) {
        var im_eva_first: ImageView = view.findViewById(R.id.im_eva_first)
        var tv_eva_first: TextView = view.findViewById(R.id.tv_eva_first)

    }

}