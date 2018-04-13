package com.teach.guanjianhui.teachquality.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * 基类
 * Created by guanjianhui on 18-1-26.
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStatusBarTransparent()

        setContentView(layoutId())

        //var rootView = window.decorView.findViewById<ViewGroup>(android.R.id.content)

        initView()
        initData()
        initListener()
    }

    /**
     * 利用反射获取状态栏高度
     * @return
     */
    fun getStatusBarHeight(): Int {
        var result = 0;
        //获取状态栏高度的资源id
        var resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;

    }

    /**
     * 设置状态栏字体颜色为深色
     */
    fun setFontDark() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                val decorView = this.window.decorView
                val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                decorView.systemUiVisibility = option
            }
        }

    }

    fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                val decorViewClazz = Class.forName("com.android.internal.policy.DecorView")
                val field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor")
                field.isAccessible = true
                field.setInt(window.decorView, Color.TRANSPARENT)  //改为透明
            } catch (e: Exception) {

            }

        }
    }


    //初始化监听器
    abstract fun initListener()

    //初始化数据
    abstract fun initData()

    //初始化view参数
    abstract fun initView()

    //初始化布局id
    abstract fun layoutId(): Int

    //取消订阅，避免内存泄露
    abstract fun detachView()

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onDestroy() {
        super.onDestroy()
        detachView()
    }
}