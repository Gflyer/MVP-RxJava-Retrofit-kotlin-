package com.teach.guanjianhui.teachquality.ui

import android.content.Intent
import android.os.Handler
import android.os.Message
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.ui.teach.activity.LoginActivity
import com.teach.guanjianhui.teachquality.utils.ToastUtils


/**
 * 滑动导航activity
 * Created by guanjianhui on 18-3-22.
 */
class SplashActivity : BaseActivity(), SplashContact.View {

    //可加handler,延迟启动
    val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0x01 -> {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }

    val mPresenter by lazy { SplashPresenter() }

    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        setFontDark()
        mPresenter.attachView(this)
    }

    override fun initData() {
        mPresenter.saveEvaluateData()
    }

    override fun initListener() {
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun saveSuccess(msg: String) {
        //ToastUtils.showToast(this, msg)
    }

    override fun saveFailure() {
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    override fun startActivity() {
        var message = Message.obtain()
        message.what = 0x01
        handler.sendMessageDelayed(message, 3000)

    }

}