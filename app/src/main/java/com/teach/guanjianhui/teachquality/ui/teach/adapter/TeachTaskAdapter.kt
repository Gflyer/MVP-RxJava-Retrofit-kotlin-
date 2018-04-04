package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.TeachTaskBean

/**
 * Created by guanjianhui on 18-3-29.
 */
class TeachTaskAdapter(var ctx: Context, var myList: List<TeachTaskBean>) : RecyclerView.Adapter<TeachTaskAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        var view = LayoutInflater.from(ctx).inflate(R.layout.item_teach_task, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyHolder?, position: Int) {
        holder!!.tv_teach_task_id.text = myList[position].id.toString()
        holder!!.tv_teach_task_course.text = myList[position].courseName
        holder!!.tv_teach_task_name.text = myList[position].teacherName
        holder!!.tv_teach_task_term.text = myList[position].termDate
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_teach_task_course: TextView = itemView.findViewById<TextView>(R.id.tv_teach_task_course)
        var tv_teach_task_id = itemView.findViewById<TextView>(R.id.tv_teach_task_id)
        var tv_teach_task_name = itemView.findViewById<TextView>(R.id.tv_teach_task_name)
        var tv_teach_task_term = itemView.findViewById<TextView>(R.id.tv_teach_task_term)


    }

}