package com.teach.guanjianhui.teachquality.ui.teach.fragment

import android.content.Intent
import android.os.Bundle
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseFragment
import com.teach.guanjianhui.teachquality.ui.teach.activity.LeakCanaryActivity
import kotlinx.android.synthetic.main.fragment_selected.*

/**
 * 热门模块
 * Created by guanjianhui on 18-1-29.
 */

class HotFragment : BaseFragment() {
    var mTitle: String? = null

    override fun getLayoutId(): Int = R.layout.fragment_selected
    override fun initView() {
        tv_selected.setText("我是" + mTitle + "模块")
    }

    override fun initData() {

    }

    override fun initListener() {
        btn_plus.setOnClickListener {
            val intent = Intent(activity, LeakCanaryActivity::class.java)
            startActivity(intent)
        }
    }

    //相当于static
    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }

    }

}