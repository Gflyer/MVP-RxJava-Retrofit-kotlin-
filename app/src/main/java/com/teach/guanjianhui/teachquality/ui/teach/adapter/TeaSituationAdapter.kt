package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.TeaSituationBean

/**
 * Created by guanjianhui on 18-4-10.
 */
class TeaSituationAdapter(var ctx: Context, var myList: List<TeaSituationBean>) : RecyclerView.Adapter<TeaSituationAdapter.MyHolder>() {
    var myListener: TeaSituationAdapter.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_tea_stu_situation, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyHolder?, position: Int) {
        holder?.tv_tea_manager_id?.text = myList!![position]?.id.toString()
        holder?.tv_tea_manager_institute?.text = myList!![position]?.institute
        holder?.tv_tea_manager_name?.text = myList!![position]?.teacherName
        holder?.tv_tea_manager_course?.text = myList!![position]?.courseName
        holder?.tv_tea_manager_avg_score?.text = myList!![position]?.avg_score
        holder?.itemView?.setOnClickListener {
            myListener?.onClick(position)
        }
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_tea_manager_id = itemView.findViewById<TextView>(R.id.tv_tea_manager_id)
        val tv_tea_manager_institute = itemView.findViewById<TextView>(R.id.tv_tea_manager_institute)
        val tv_tea_manager_name = itemView.findViewById<TextView>(R.id.tv_tea_manager_name)
        val tv_tea_manager_course = itemView.findViewById<TextView>(R.id.tv_tea_manager_course)
        val tv_tea_manager_avg_score = itemView.findViewById<TextView>(R.id.tv_tea_manager_avg_score)

    }

    fun setOnItemClickListener(myListener: OnItemClickListener) {
        this.myListener = myListener
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }
}