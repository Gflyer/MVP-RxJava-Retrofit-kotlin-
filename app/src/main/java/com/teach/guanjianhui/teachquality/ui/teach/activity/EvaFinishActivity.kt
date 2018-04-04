package com.teach.guanjianhui.teachquality.ui.teach.activity

import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import kotlinx.android.synthetic.main.activity_evafinish.*

/**
 * Created by guanjianhui on 18-3-27.
 */
class EvaFinishActivity : BaseActivity() {
    override fun initListener() {
        btn_eva_finish.setOnClickListener {
            finish()
        }
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_evafinish
    }

    override fun detachView() {
    }

}