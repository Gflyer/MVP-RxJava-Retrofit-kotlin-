package com.teach.guanjianhui.teachquality.ui.teach.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseFragment
import com.teach.guanjianhui.teachquality.ui.teach.fragment.testAac.TestViewModel
import kotlinx.android.synthetic.main.fragment_mine_test.*
import kotlinx.android.synthetic.main.fragment_selected.*

/**
 * 我的模块
 * Created by guanjianhui on 18-1-29.
 */
//paging library宣布放弃,后会有期
class MineFragment : BaseFragment() {

    private var mTitle: String? = null
    //   private val testViewModel: TestViewModel by lazy { ViewModelProviders.of(this).get(TestViewModel::class.java) }

    override fun getLayoutId(): Int = R.layout.fragment_mine_test

    override fun initView() {
//        testViewModel.getUserId().observe(this, (Observer { testReponseBean ->
//            if (testReponseBean != null) {
//
//            }
//        }
//                )
//        )
        rlv_mine.adapter

    }

    override fun initData() {

    }

    override fun initListener() {

    }

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle//用于activity与fragment传递数据
            fragment.mTitle = title
            return fragment

        }
    }
}
