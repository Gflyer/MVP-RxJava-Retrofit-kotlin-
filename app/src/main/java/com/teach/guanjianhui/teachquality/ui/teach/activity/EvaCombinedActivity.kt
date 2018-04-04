package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.support.v4.content.ContextCompat
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.widgets.CombinedChartManager
import kotlinx.android.synthetic.main.activity_eva_combined.*
import java.util.ArrayList

/**
 * Created by guanjianhui on 18-4-2.
 */
class EvaCombinedActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_eva_combined
    }

    override fun initView() {
        setFontDark()
    }

    override fun initData() {
        initCombineData()
    }

    override fun initListener() {
    }

    override fun detachView() {
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


        val combineChartManager = CombinedChartManager(chart_eva_combined, this)
        chart_eva_combined.description.textSize=12f
        combineChartManager.showCombinedChart(xData, yBarDatas, yBarDatas, barNames, lineNames,
                colors, colors)

    }

}