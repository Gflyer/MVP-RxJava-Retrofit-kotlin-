package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.support.v4.content.ContextCompat
import android.util.Log
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import kotlinx.android.synthetic.main.activity_teacher_detial.*
import java.util.*

/**
 * Created by guanjianhui on 18-3-30.
 */
class TotalDetialActivity : BaseActivity() {

    val xEntries = ArrayList<String>()
    var teacherEntries = ArrayList<Entry>()


    override fun layoutId(): Int {
        return R.layout.activity_teacher_detial
    }

    override fun initView() {

        setFontDark()
        //获取x轴
        val xAxis = lc_teacher_detial.xAxis
        //获取右Y轴
        val yAxisRight = lc_teacher_detial.axisRight

        //折线图显示边界
        lc_teacher_detial.setDrawBorders(true)

        //设置x轴在下方
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setLabelCount(6, false)
        xAxis.axisMinimum = 0f
        xAxis.axisMaximum = 5f

        yAxisRight.isEnabled = false
        val des = Description()
        des.text = "学期"
        des.textSize = 15f
        des.textColor = ContextCompat.getColor(this, R.color.clo_dark_gray)
        lc_teacher_detial.description = des
    }

    override fun initData() {
        // var teacherEntries = ArrayList<Entry>()

        xEntries.apply {
            add("2015-2016-1")
            add("2015-2016-2")
            add("2016-2017-1")
            add("2016-2017-2")
            add("2017-2018-1")
            add("2017-2018-2")
        }

        //模拟数据
        for (i in 0..5) {
            teacherEntries.add(Entry(i.toFloat(), (Math.random() * 20 + 80).toFloat()))
        }

        //一个lineDataset对应一条线
        val lineDataset = LineDataSet(teacherEntries, "总分")
        lineDataset.setDrawCircleHole(false)
        lineDataset.lineWidth = 5f
        lineDataset.setColor(ContextCompat.getColor(this, R.color.clo_status_dark_green), 180)
        lineDataset.valueTextSize = 10f
        lineDataset.circleSize = 6f
        lineDataset.valueTextColor = ContextCompat.getColor(this, R.color.clo_dark_gray)
        val totalScoreData = LineData(lineDataset)
        lc_teacher_detial.data = totalScoreData

    }

    override fun initListener() {
        val xAxis = lc_teacher_detial.xAxis

        xAxis.setValueFormatter { value, axis ->
            Log.i("value.toInt()", value.toInt().toString())
            return@setValueFormatter xEntries.get(value.toInt())
        }

    }

    override fun detachView() {
    }

}