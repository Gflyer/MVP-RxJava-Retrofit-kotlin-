package com.teach.guanjianhui.teachquality.ui.teach.fragment

import android.os.Bundle
import android.widget.Toast
import com.raizlabs.android.dbflow.rx2.kotlinextensions.save
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
            TeachTable.StudentInfo("3114001443", "guan", "女", "2").save { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(activity, "成功", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "失败", Toast.LENGTH_SHORT).show()
                }
            }


//            select.from(TeachTable.StudentInfo::class.java)
//                    .rx()
//                    .list { list ->
//
//                    }
        }//lambda表达式优化
    }

    private fun getData() {
        mPresenter.getData()
    }

    override fun loadSuccess(res: Int) {
        Toast.makeText(activity, "我是" + res, Toast.LENGTH_SHORT).show()
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