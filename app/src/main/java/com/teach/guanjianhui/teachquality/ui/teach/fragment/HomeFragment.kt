package com.teach.guanjianhui.teachquality.ui.teach.fragment

import android.os.Bundle
import android.widget.Toast
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseFragment
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.ui.teach.contract.HomeContract
import com.teach.guanjianhui.teachquality.ui.teach.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_selected.*

/**
 * 每日精选模块
 * Created by guanjianhui on 18-1-29.
 */

class HomeFragment : BaseFragment(), HomeContract.View {

    override fun loadSuccess(list: List<TeachTable.StudentInfo>) {
        for (item in list) {
            Toast.makeText(activity, item.studentNum, Toast.LENGTH_SHORT).show()
        }
    }

    private val mPresenter by lazy { HomePresenter() }

    private var mTitle: String? = null

    override fun getLayoutId(): Int = R.layout.fragment_selected

    override fun initView() {
        mPresenter.attachView(this)
        tv_selected.setText("我是" + mTitle + "模块")
    }

    override fun initData() {

    }

    override fun initListener() {
        btn_plus.setOnClickListener {
            getData()
        }//lambda表达式优化
    }

    private fun getData() {
        mPresenter.getData()
    }

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle//用于activity与fragment之间通信
            fragment.mTitle = title
            return fragment
        }
    }
}