package com.teach.guanjianhui.teachquality.widgets

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup

/**
 * 悬浮列表item
 * Created by guanjianhui on 18-3-23.
 */
class SuspendDecoration : RecyclerView.ItemDecoration {

    constructor(suspendListener: SuspendGroupListener) {
        this.suspendListener = suspendListener
    }

    private var suspendListener: SuspendGroupListener? = null
    private val mGroupHeight = 100


    //通过getItemOffsets（）为item上下预留空间进行二级绘制
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        var pos = parent!!.getChildAdapterPosition(view)
        var groupId: String? = suspendListener!!.getGroupName(pos)
        groupId ?: return

        //为顶部view预留头空间(第一个或者发生变化时)
        if (isFirstGroup(pos)) {
            outRect!!.top = mGroupHeight
        }

    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
        val itemCount = state!!.itemCount//recView总的item数量
        val childCount = parent!!.childCount//当前屏幕可见的item数量
        val left = parent.left + parent.paddingLeft
        val right = parent.right - parent.paddingRight
        var preGroupName: String?
        var curGroupName: String? = null
        loop@ for (i in 0..childCount - 1) {
            var view = parent.getChildAt(i)
            var position = parent.getChildAdapterPosition(view)
            preGroupName = curGroupName
            curGroupName = suspendListener!!.getGroupName(position)

            //获取第一个pos
            if (i == 0) {
                suspendListener!!.getTopPos(position)
            }


            if (curGroupName == null || TextUtils.equals(curGroupName, preGroupName)) {
                continue@loop
            }

            var viewBottom = view.bottom
            var top: Float = Math.max(mGroupHeight, view.top).toFloat()

            //第一个悬浮Group的位置
            if (position + 1 < itemCount) {
                var nextGroupName = suspendListener!!.getGroupName(position + 1)
                if (!curGroupName.equals(nextGroupName) && viewBottom < top) {
                    top = viewBottom.toFloat()
                }
            }

            //c.drawRect(left,top-mGroupHeight,right,top,)

            var groupView = suspendListener!!.getGroupView(position)
            if (groupView == null) return
            var layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mGroupHeight)
            groupView.layoutParams = layoutParams
            groupView.isDrawingCacheEnabled = true
            groupView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))

            //指定高度、宽度的groupView
            groupView.layout(0, 0, right, mGroupHeight)
            groupView.buildDrawingCache()
            groupView.isDrawingCacheEnabled=true
            var bitmap = groupView.getDrawingCache()

            c!!.drawBitmap(bitmap, 0.toFloat(), top - mGroupHeight, null)//left

        }


    }

    private fun isFirstGroup(pos: Int): Boolean {
        if (pos == 0) {
            return true
        } else {
            var prevGroupId = suspendListener!!.getGroupName(pos - 1)
            var groupId = suspendListener!!.getGroupName(pos)
            return !TextUtils.equals(prevGroupId, groupId)
        }
    }

    //接口获取二级信息
    interface SuspendGroupListener {
        //当前大组的名字
        fun getGroupName(position: Int): String?

        fun getGroupView(position: Int): View?

        //获取当前顶部位置
        fun getTopPos(position: Int)

    }
}