package com.teach.guanjianhui.teachquality.widgets

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.teach.guanjianhui.teachquality.R

/**
 * Created by guanjianhui on 18-4-2.
 */
class CombinedChartManager(var mCombinedChart: CombinedChart, var ctx: Context) {

    private fun initChart(xAxisValues: List<String>) {
        mCombinedChart.description.isEnabled = true
        mCombinedChart.description.text = "学期"
        mCombinedChart.description.textColor = ContextCompat.getColor(ctx, R.color.clo_normal_white)
        mCombinedChart.setBackgroundColor(Color.WHITE)
        mCombinedChart.setDrawBarShadow(false)
        mCombinedChart.isHighlightFullBarEnabled = false

        // draw bar behind lines
        mCombinedChart.drawOrder = arrayOf(CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.BAR)

        val l = mCombinedChart.legend
        l.isWordWrapEnabled = true
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = Legend.LegendForm.SQUARE
//        l.formSize = 9f
//        l.textSize = 11f
//        l.xEntrySpace = 4f
        mCombinedChart.setDrawValueAboveBar(false)
        mCombinedChart.setDrawBarShadow(false)
        mCombinedChart.setPinchZoom(true)

        val rightAxis = mCombinedChart.axisRight
        rightAxis.isEnabled = false
//        rightAxis.setDrawGridLines(false)
//        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

        val leftAxis = mCombinedChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = 60f

        val xAxis = mCombinedChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f
        xAxis.setValueFormatter { value, axis ->
            return@setValueFormatter xAxisValues[(value % xAxisValues.size).toInt()]
        }


    }

    private fun getLineData(lineChartYs: List<List<Float>>, lineNames: List<String>, lineColors: List<Int>): LineData {

        val d = LineData()

        for (i in lineChartYs.indices) {
            val yValues = ArrayList<Entry>()
            for (j in 0 until lineChartYs[i].size) {
                if (i == 0) {
                    yValues.add(Entry(j.toFloat() + 0.255f, lineChartYs[i][j]))
                } else if (i == 1) {
                    yValues.add(Entry(j.toFloat() + 0.725f, lineChartYs[i][j]))

                }
            }

            val dataSet = LineDataSet(yValues, null)
            dataSet.color = lineColors[i]
            dataSet.setCircleColor(lineColors[i])
            dataSet.valueTextColor = lineColors[i]


            dataSet.circleSize = 2f
            dataSet.setDrawValues(true)
            dataSet.valueTextSize = 10f

            dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            dataSet.axisDependency = YAxis.AxisDependency.LEFT
            d.addDataSet(dataSet)
        }
        return d
    }

    private fun getBarData(barChartYs: List<List<Float>>, barNames: List<String>, barColors: List<Int>): BarData {
        val lists = ArrayList<IBarDataSet>()

        for (i in barChartYs.indices) {
            val entries = ArrayList<BarEntry>()

            for (j in 0 until barChartYs[i].size) {
                entries.add(BarEntry(j.toFloat(), barChartYs[i][j]))
            }
            val barDataSet = BarDataSet(entries, barNames[i])

            barDataSet.color = barColors[i]
            barDataSet.valueTextColor = barColors[i]
            barDataSet.valueTextSize = 10f
            barDataSet.axisDependency = YAxis.AxisDependency.LEFT
            lists.add(barDataSet)
        }
        val barData = BarData(lists)

        val amount = barChartYs.size //需要显示柱状图的类别 数量
//        val groupSpace = 0.12f //柱状图组之间的间距
//        val barSpace = ((1 - 0.12) / amount.toDouble() / 10.0).toFloat() // x4 DataSet
//        val barWidth = ((1 - 0.12) / amount.toDouble() / 10.0 * 9).toFloat() // x4 DataSet
        val groupSpace = 0.06f
        val barSpace = 0.02f
        val barWidth = 0.45f
        // (0.2 + 0.02) * 4 + 0.12 = 1.00 即100% 按照百分百布局
        //柱状图宽度
        barData.barWidth = barWidth
        //(起始点、柱状图组间距、柱状图之间间距)
        barData.groupBars(0f, groupSpace, barSpace)
        return barData
    }

    fun showCombinedChart(
            xAxisValues: List<String>, barChartYs: List<List<Float>>, lineChartYs: List<List<Float>>,
            barNames: List<String>, lineNames: List<String>, barColors: List<Int>, lineColors: List<Int>) {
        initChart(xAxisValues)


        val combinedData = CombinedData()

        combinedData.setData(getBarData(barChartYs, barNames, barColors))
        combinedData.setData(getLineData(lineChartYs, lineNames, lineColors))


        val xAxis = mCombinedChart.xAxis
        xAxis.axisMaximum = combinedData.getXMax() + 0.25f


        mCombinedChart.data = combinedData
        mCombinedChart.invalidate()
    }

}