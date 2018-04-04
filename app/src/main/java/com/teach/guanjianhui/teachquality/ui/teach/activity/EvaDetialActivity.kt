package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.content.Intent
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.widgets.CombinedChartManager
import kotlinx.android.synthetic.main.activity_eva_detial.*
import kotlinx.android.synthetic.main.tool_title.*
import java.util.ArrayList
import com.teach.guanjianhui.teachquality.widgets.CombinedChartManager2


/**
 * Created by guanjianhui on 18-3-30.
 */
class EvaDetialActivity : BaseActivity() {

    val xEntries = ArrayList<String>()
    var teacherEntries = ArrayList<Entry>()

    override fun layoutId(): Int {
        return R.layout.activity_eva_detial
    }

    override fun initView() {
        tv_title.text = "评分统计详情"

        //获取x轴
        val xAxis = lineChart_total.xAxis
        //获取右Y轴
        val yAxisRight = lineChart_total.axisRight

        //折线图显示边界
        lineChart_total.setDrawBorders(true)

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
        lineChart_total.description = des
    }

    override fun initData() {
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
        lineChart_total.data = totalScoreData

        initCombineData()
    }

    private fun initCombineData() {
        //x轴数据
        val xData = ArrayList<String>()
        xData.apply {
            add("2013-2014-1")
            add("2013-2014-2")
            add("2014-2015-1")
            add("2014-2015-2")
            add("2015-2016-1")
            add("2015-2016-2")
            add("2016-2017-1")
            add("2016-2017-2")
            add("2017-2018-1")
            add("2017-2018-2")
        }
        //y轴数据集合,柱状图
        val yBarDatas = ArrayList<List<Float>>()

        //2种直方图
        for (i in 0..1) {
            //y轴数
            val yData = ArrayList<Float>()
            for (j in 0..9) {
                yData.add((Math.random() * 20 + 80).toFloat())
            }
            yBarDatas.add(yData)
        }

        //y轴数据集合,折线图
        val yLineDatas = ArrayList<List<Float>>()
        //2种折线图
        for (i in 0..1) {
            //y轴数
            val yData = ArrayList<Float>()
            for (j in 0..9) {
                yData.add((Math.random() * 20 + 80).toFloat())
            }
            yLineDatas.add(yData)
        }

        //名字集合
        val barNames = ArrayList<String>()
        barNames.add("综合评教平均分")
        barNames.add("学生评教平均分")

        //颜色集合
        val colors = ArrayList<Int>()
        colors.add(ContextCompat.getColor(this, R.color.clo_chart_green))
        colors.add(ContextCompat.getColor(this, R.color.clo_chart_blue))

        //竖状图管理类

        val lineNames = ArrayList<String>()
        lineNames.add("")
        lineNames.add("")


        val combineChartManager = CombinedChartManager(combinedChart_detial, this)
        combineChartManager.showCombinedChart(xData, yBarDatas, yBarDatas, barNames, lineNames,
                colors, colors)

    }

    override fun initListener() {
        val xAxis = lineChart_total.xAxis

        xAxis.setValueFormatter { value, axis ->
            return@setValueFormatter xEntries.get(value.toInt())
        }

        im_lineChart_enlarge.setOnClickListener {
            startActivity(Intent(this, TotalDetialActivity::class.java))
        }

        im_comChart_enlarge.setOnClickListener {
            startActivity(Intent(this, EvaCombinedActivity::class.java))
        }

        tool_title.setNavigationOnClickListener {
            finish()
        }
    }

    override fun detachView() {
    }

}