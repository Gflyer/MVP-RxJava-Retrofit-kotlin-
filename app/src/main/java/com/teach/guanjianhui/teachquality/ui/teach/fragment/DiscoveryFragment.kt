package com.teach.guanjianhui.teachquality.ui.teach.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseFragment
import com.teach.guanjianhui.teachquality.ui.teach.contract.DiscoveryContract
import com.teach.guanjianhui.teachquality.ui.teach.presenter.DiscoveryPresenter
import kotlinx.android.synthetic.main.fragment_selected.*

/**
 * 发现模块
 * Created by guanjianhui on 18-1-29.
 */
class DiscoveryFragment : BaseFragment(), DiscoveryContract.View, View.OnClickListener {
    private val mPresenter: DiscoveryPresenter by lazy { DiscoveryPresenter() }

    private var mTitle: String? = null

    override fun initView() {
        mPresenter.attachView(this)//为presenter绑定view
        tv_selected.setText("我是" + mTitle + "模块")
    }

    override fun initData() {

    }

    override fun initListener() {
        btn_plus.setOnClickListener(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_selected

    override fun onClick(v: View?) {
        when (v?.id) {
        R.id.btn_plus
        -> mPresenter.getData() //开始获取数据
        }

    }
    override fun detachView() {
        mPresenter.detachView()
    }

    //相当于static函数,创建对应的fragment对象
    companion object {
        fun getInstance(title: String): DiscoveryFragment {
            val fragment = DiscoveryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun showSuccess(res: Int) {
        Toast.makeText(activity, "我是" + res, Toast.LENGTH_SHORT).show()
    }

}
