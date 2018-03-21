package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teach.guanjianhui.teachquality.R

/**
 * Created by guanjianhui on 18-3-21.
 */
class EvaluateListAdapter(var list:List<String>?,var context:Context) : RecyclerView.Adapter<EvaluateListAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
    var view=LayoutInflater.from(context).inflate(R.layout.item_evaluate,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
    return list?.size?:0
    }

    override fun onBindViewHolder(holder: MyHolder?, position: Int) {

    }


    class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

}