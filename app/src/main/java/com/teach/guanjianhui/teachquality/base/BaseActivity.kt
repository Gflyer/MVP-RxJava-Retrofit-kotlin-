package com.teach.guanjianhui.teachquality.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.bmob.v3.Bmob

/**
 * 基类
 * Created by guanjianhui on 18-1-26.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Bmob.initialize(this, "1fc8ca2b8ae58d5755e7f1d453e15ed6");

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