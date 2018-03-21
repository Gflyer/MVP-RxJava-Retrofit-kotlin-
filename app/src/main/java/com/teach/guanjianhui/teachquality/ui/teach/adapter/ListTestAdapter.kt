package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.ListItemBean

/**
 * Created by guanjianhui on 18-3-8.
 */
class ListTestAdapter(var itemList: ArrayList<ListItemBean>?, val context: Context) : Adapter<ListTestAdapter.MineViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListTestAdapter.MineViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mine_item, parent, false)
        val myViewHolder = MineViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0//?:左边为true，表示true时返回size大小，false返回右边0
    }


    override fun onBindViewHolder(holder: ListTestAdapter.MineViewHolder?, position: Int) {
        holder?.mTv_mine?.setText(itemList?.get(position)?.name)
    }

    class MineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mIm_mine: ImageView
        var mTv_mine: TextView

        init {
            mIm_mine = itemView.findViewById(R.id.im_mine)
            mTv_mine = itemView.findViewById(R.id.tv_mine)
        }
    }


}
