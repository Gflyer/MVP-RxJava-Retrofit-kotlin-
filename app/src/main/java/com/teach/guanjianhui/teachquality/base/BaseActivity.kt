package com.teach.guanjianhui.teachquality.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * 基类
 * Created by guanjianhui on 18-1-26.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        initData()
        initListener()
    }

    //初始化监听器
    abstract fun initListener()

    //初始化数据
    abstract fun initData()

    //初始化view参数
    abstract fun initView()

    //初始化布局id
    abstract fun layoutId(): Int


    override fun onDestroy() {
        super.onDestroy()
    }
}