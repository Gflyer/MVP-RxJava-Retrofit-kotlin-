package com.teach.guanjianhui.teachquality.widgets

import android.graphics.Color
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.data.CombinedData


/**
 * Created by guanjianhui on 18-3-30.
 */
class CombinedChartManager2(var mCombinedChart: CombinedChart) {
    private var leftAxis: YAxis? = null
    private var rightAxis: YAxis? = null
    private var xAxis: XAxis? = null

    init {
        leftAxis = mCombinedChart.getAxisLeft();
        rightAxis = mCombinedChart.getAxisRight();
        xAxis = mCombinedChart.getXAxis();
    }

    /**
     * 初始化Chart
     */
    private fun initChart() {
        //不显示描述内容
        mCombinedChart.description.isEnabled = false

        mCombinedChart.drawOrder = arrayOf(CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE)

        mCombinedChart.setBackgroundColor(Color.WHITE)
        mCombinedChart.setDrawGridBackground(false)
        mCombinedChart.setDrawBarShadow(false)
        mCombinedChart.isHighlightFullBarEnabled = false
        //显示边界
        mCombinedChart.setDrawBorders(true)
        //图例说明
        val legend = mCombinedChart.legend
        legend.isWordWrapEnabled = true

        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        //Y轴设置
        rightAxis!!.setDrawGridLines(false)
        rightAxis!!.setAxisMinimum(0f)

        leftAxis!!.setDrawGridLines(false)
        leftAxis!!.setAxisMinimum(0f)

        mCombinedChart.animateX(2000) // 立即执行的动画,x轴
    }

    /**
     * 设置X轴坐标值
     *
     * @param xAxisValues x轴坐标集合
     */
    fun setXAxis(xAxisValues: List<String>) {

        //设置X轴在底部
        val xAxis = mCombinedChart.xAxis
        val yAxisLeft = mCombinedChart.axisLeft
        val yAxisRight = mCombinedChart.axisRight

        yAxisLeft.mAxisMinimum = 60f
        yAxisRight.isEnabled = false

        //yAxisLeft.setLabelCount(1,true)
        //yAxisLeft.xOffset=60f

        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f

        xAxis.setLabelCount(xAxisValues.size - 1, false)
        xAxis.valueFormatter = IAxisValueFormatter { value, axis -> xAxisValues[value.toInt() % xAxisValues.size] }
        mCombinedChart.invalidate()
    }

    /**
     * 得到折线图(一条)
     *
     * @param lineChartY 折线Y轴值
     * @param lineName   折线图名字
     * @param lineColor  折线颜色
     * @return
     */
    private fun getLineData(lineChartY: List<Float>, lineName: String, lineColor: Int): LineData {
        val lineData = LineData()

        val yValue = ArrayList<Entry>()

        for (i in lineChartY.indices) {
            yValue.add(Entry(i.toFloat(), lineChartY[i]))
        }
        val dataSet = LineDataSet(yValue, lineName)

        dataSet.color = lineColor
        dataSet.setCircleColor(lineColor)
        dataSet.valueTextColor = lineColor

        dataSet.circleSize = 1f
        //显示值
        dataSet.setDrawValues(true)
        dataSet.valueTextSize = 10f
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.axisDependency = YAxis.AxisDependency.LEFT
        lineData.addDataSet(dataSet)
        return lineData
    }

    /**
     * 得到折线图(多条)
     *
     * @param lineChartYs 折线Y轴值
     * @param lineNames   折线图名字
     * @param lineColors  折线颜色
     * @return
     */
    private fun getLineData(lineChartYs: List<List<Float>>, lineNames: List<String>, lineColors: List<Int>): LineData {
        val lineData = LineData()

        //xAxis.setAxisMaximum(1.0f)

        for (i in lineChartYs.indices) {
            val yValues = ArrayList<Entry>()
            for (j in 0 until lineChartYs[i].size) {
                yValues.add(Entry(j.toFloat()+0.5f, lineChartYs[i][j]))
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
            lineData.addDataSet(dataSet)
        }
        return lineData
    }

    /**
     * 得到柱状图
     *
     * @param barChartY Y轴值
     * @param barName   柱状图名字
     * @param barColor  柱状图颜色
     * @return
     */

    private fun getBarData(barChartY: List<Float>, barName: String, barColor: Int): BarData {
        val barData = BarData()
        val yValues = ArrayList<BarEntry>()

        for (i in barChartY.indices) {
            yValues.add(BarEntry(i.toFloat(), barChartY[i]))
        }

        val barDataSet = BarDataSet(yValues, barName)
        barDataSet.color = barColor
        barDataSet.valueTextSize = 10f
        barDataSet.valueTextColor = barColor
        barDataSet.axisDependency = YAxis.AxisDependency.LEFT
        barData.addDataSet(barDataSet)

        //以下是为了解决 柱状图 左右两边只显示了一半的问题 根据实际情况 而定
        xAxis!!.setAxisMinimum(-0.5f)
        xAxis!!.setAxisMaximum((barChartY.size - 0.5).toFloat())
        return barData
    }

    /**
     * 得到柱状图(多条)
     *
     * @param barChartYs Y轴值
     * @param barNames   柱状图名字
     * @param barColors  柱状图颜色
     * @return
     */

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
        val groupSpace = 0.12f //柱状图组之间的间距
        val barSpace = ((1 - 0.12) / amount.toDouble() / 10.0).toFloat() // x4 DataSet
        val barWidth = ((1 - 0.12) / amount.toDouble() / 10.0 * 9).toFloat() // x4 DataSet

        // (0.2 + 0.02) * 4 + 0.12 = 1.00 即100% 按照百分百布局
        //柱状图宽度
        barData.setBarWidth(barWidth)
        //(起始点、柱状图组间距、柱状图之间间距)
        barData.groupBars(0f, groupSpace, barSpace)
        return barData
    }

    /**
     * 显示混合图(柱状图+折线图)
     *
     * @param xAxisValues X轴坐标
     * @param barChartY   柱状图Y轴值
     * @param lineChartY  折线图Y轴值
     * @param barName     柱状图名字
     * @param lineName    折线图名字
     * @param barColor    柱状图颜色
     * @param lineColor   折线图颜色
     */

    fun showCombinedChart(
            xAxisValues: List<String>, barChartY: List<Float>, lineChartY: List<Float>, barName: String, lineName: String, barColor: Int, lineColor: Int) {
        initChart()
        setXAxis(xAxisValues)

        val combinedData = CombinedData()

        combinedData.setData(getBarData(barChartY, barName, barColor))
        combinedData.setData(getLineData(lineChartY, lineName, lineColor))
        mCombinedChart.data = combinedData
        mCombinedChart.invalidate()
    }


    /**
     * 显示混合图(柱状图+折线图)
     *
     * @param xAxisValues X轴坐标
     * @param barChartYs  柱状图Y轴值
     * @param lineChartYs 折线图Y轴值
     * @param barNames    柱状图名字
     * @param lineNames   折线图名字
     * @param barColors   柱状图颜色
     * @param lineColors  折线图颜色
     */

    fun showCombinedChart(
            xAxisValues: List<String>, barChartYs: List<List<Float>>, lineChartYs: List<List<Float>>,
            barNames: List<String>, lineNames: List<String>, barColors: List<Int>, lineColors: List<Int>) {
        initChart()
        setXAxis(xAxisValues)

        val combinedData = CombinedData()

        combinedData.setData(getBarData(barChartYs, barNames, barColors))
        combinedData.setData(getLineData(lineChartYs, lineNames, lineColors))

        mCombinedChart.data = combinedData
        mCombinedChart.invalidate()
    }

}
