package com.teach.guanjianhui.teachquality.ui.teach.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.EvaluateStuBean
import com.teach.guanjianhui.teachquality.constant.Field

/**
 * Created by guanjianhui on 18-3-23.
 */
class StudentEvAdapter(var ctx: Context, var list: List<EvaluateStuBean>?) : RecyclerView.Adapter<StudentEvAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        var view = LayoutInflater.from(ctx).inflate(R.layout.item_evaluate_stu, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }


    override fun onBindViewHolder(holder: MyHolder?, position: Int) {
        holder?.tv_num!!.setText("" + list!![position].num)
        holder?.tv_tip!!.setText(list!![position].evaluate)

        holder?.rag_stu_eva.setOnCheckedChangeListener(null)//取消监听,解决radioGroup滑动混乱

        if (list!![position].scoreType == 0x10) {
            holder?.rag_stu_eva.clearCheck()
        } else {

            when (list!![position].scoreType) {
                Field.EVALUATE_EXCELLENT -> holder?.rab_stu_excellent.isChecked = true
                Field.EVALUATE_GOOD -> holder?.rab_stu_good.isChecked = true
                Field.EVALUATE_QUALITY -> holder?.rab_stu_quality.isChecked = true
                Field.EVALUATE_UNQUALITY -> holder?.rab_stu_unquality.isChecked = true
            }

        }

        holder?.rag_stu_eva.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rab_stu_excellent -> list!!.get(position).scoreType = Field.EVALUATE_EXCELLENT
                R.id.rab_stu_good -> list!!.get(position).scoreType = Field.EVALUATE_GOOD
                R.id.rab_stu_quality -> list!!.get(position).scoreType = Field.EVALUATE_QUALITY
                R.id.rab_stu_unquality -> list!!.get(position).scoreType = Field.EVALUATE_UNQUALITY
            }
        }


    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_num: TextView
        var tv_tip: TextView
        var rab_stu_excellent: RadioButton
        var rab_stu_good: RadioButton
        var rab_stu_quality: RadioButton
        var rab_stu_unquality: RadioButton
        var rag_stu_eva: RadioGroup


        init {
            tv_num = itemView.findViewById(R.id.tv_num)
            tv_tip = itemView.findViewById(R.id.tv_tip)
            rab_stu_excellent = itemView.findViewById(R.id.rab_stu_excellent)
            rab_stu_good = itemView.findViewById(R.id.rab_stu_good)
            rab_stu_quality = itemView.findViewById(R.id.rab_stu_quality)
            rab_stu_unquality = itemView.findViewById(R.id.rab_stu_unquality)
            rag_stu_eva = itemView.findViewById(R.id.rag_stu_eva)
        }
    }
}


