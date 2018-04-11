package com.teach.guanjianhui.teachquality.widgets

import android.content.Context
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.view.View
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.beans.TeaSituationDetailBean
import com.teach.guanjianhui.teachquality.ui.teach.adapter.DialogAdapter
import kotlinx.android.synthetic.main.dialog_viewpager.*

/**
 * Created by guanjianhui on 18-4-11.
 */
class ViewPagerDialog(val ctx: Context, val myList: List<TeaSituationDetailBean>) : AlertDialog(ctx, R.style.StudentEvaDialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setWindowAnimations(R.style.Animation_AppCompat_DropDownUp)//设置dialog弹出动画
        setContentView(R.layout.dialog_viewpager)//设置dialog布局
        initView()
    }

    private fun initView() {
        vp_dialog.pageMargin = 35
        vp_dialog.offscreenPageLimit = 3
        vp_dialog.setPageTransformer(false, ScaleTransFormer())
        vp_dialog.overScrollMode=ViewPager.OVER_SCROLL_NEVER
        val myAdapter = DialogAdapter(ctx, myList)
        vp_dialog.adapter = myAdapter
        vp_dialog.currentItem = 1
    }

    class ScaleTransFormer : ViewPager.PageTransformer {
        val MIN_SCALE = 0.85f
        val MIN_ALPHA = 0.75f

        override fun transformPage(page: View?, position: Float) {
            if (position < -1 || position > 1) {
                page?.alpha = MIN_ALPHA
                page?.scaleX = MIN_SCALE
                page?.scaleY = MIN_SCALE
            } else if (position <= 1) {
                var scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                if (position < 0) {
                    var scaleX = 1 + 0.15f * position
                    page?.scaleX = scaleX
                    page?.scaleY = scaleX
                } else {
                    var scaleX = 1 - 0.15f * position
                    page?.scaleX = scaleX
                    page?.scaleY = scaleX
                }
                page?.alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)
            }
        }

    }

}