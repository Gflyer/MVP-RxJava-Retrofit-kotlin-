package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.StuSituationBean

/**
 * Created by guanjianhui on 18-4-9.
 */
class StuSituationAdapter(var ctx: Context, var myList: List<StuSituationBean>) : RecyclerView.Adapter<StuSituationAdapter.MyHolder>() {
    var myListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_eva_stu_situation, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyHolder?, position: Int) {
        holder?.tv_stu_manager_id?.text = myList[position].id.toString()
        holder?.tv_stu_manager_stu_name?.text = myList[position].studentName
        holder?.tv_stu_manager_tea_name?.text = myList[position].teacherName
        holder?.tv_stu_manager_course?.text = myList[position].courseName
        holder?.tv_stu_manager_total_score?.text = myList[position].totalScore.toString()
        holder?.itemView?.setOnClickListener {
            myListener?.onClick(position)
        }
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_stu_manager_id = itemView.findViewById<TextView>(R.id.tv_stu_manager_id)
        val tv_stu_manager_stu_name = itemView.findViewById<TextView>(R.id.tv_stu_manager_stu_name)
        val tv_stu_manager_tea_name = itemView.findViewById<TextView>(R.id.tv_stu_manager_tea_name)
        val tv_stu_manager_course = itemView.findViewById<TextView>(R.id.tv_stu_manager_course)
        val tv_stu_manager_total_score = itemView.findViewById<TextView>(R.id.tv_stu_manager_total_score)

    }

    fun setOnItemClickListener(myListener: OnItemClickListener) {
        this.myListener = myListener
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }
}