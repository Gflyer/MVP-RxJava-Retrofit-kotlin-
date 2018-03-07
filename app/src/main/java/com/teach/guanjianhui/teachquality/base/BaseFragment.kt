package com.teach.guanjianhui.teachquality.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by guanjianhui on 18-1-29.
 */
abstract class BaseFragment : Fragment(), IBaseView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayoutId(), null)//返回获得的View
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initListener()
    }

    //初始化监听
    abstract fun initListener()

    //初始化加载View
    abstract fun initView()

    //初始化加载Data
    abstract fun initData()

    //返回layoutId
    abstract fun getLayoutId(): Int

    override fun showLoading() {
        Toast.makeText(activity, "加载中", Toast.LENGTH_SHORT).show()
    }

    override fun dismissLoading() {
        Toast.makeText(activity, "加载结束", Toast.LENGTH_SHORT).show()
    }

}