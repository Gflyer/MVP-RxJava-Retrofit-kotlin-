package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.EvaluateRowBean

/**
 *
 * Created by guanjianhui on 18-3-21.
 */
class EvaluateListAdapter(var list: List<EvaluateRowBean>?, var context: Context) : RecyclerView.Adapter<EvaluateListAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_evaluate, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyHolder?, position: Int) {
        holder?.tv_num!!.setText(""+list!![position].num)
        holder?.tv_tip!!.setText(list!![position].evaluate)
        holder?.tv_excellent!!.setText(""+list!![position].excellentScore)
        holder?.tv_good!!.setText(""+list!![position].goodScore)
        holder?.tv_qualified!!.setText(""+list!![position].qualifiedScore)
        holder?.tv_unqualified!!.setText(""+list!![position].unqualifiedScore)

    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_num: TextView
        var tv_tip: TextView
        var tv_excellent: TextView
        var tv_good: TextView
        var tv_qualified: TextView
        var tv_unqualified: TextView


        init {
            tv_num = itemView.findViewById(R.id.tv_num)
            tv_tip = itemView.findViewById(R.id.tv_tip)
            tv_excellent = itemView.findViewById(R.id.tv_excellent)
            tv_good = itemView.findViewById(R.id.tv_good)
            tv_qualified = itemView.findViewById(R.id.tv_qualified)
            tv_unqualified = itemView.findViewById(R.id.tv_unqualified)
        }
    }

}