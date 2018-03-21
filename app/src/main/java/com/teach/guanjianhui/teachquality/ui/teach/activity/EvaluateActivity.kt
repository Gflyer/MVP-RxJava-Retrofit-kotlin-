package com.teach.guanjianhui.teachquality.ui.teach.activity

import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.beans.EvaluateItemBean
import com.teach.guanjianhui.teachquality.ui.teach.adapter.EvaluateAdapter
import com.teach.guanjianhui.teachquality.ui.teach.contract.EvaluateContract
import com.teach.guanjianhui.teachquality.ui.teach.presenter.EvaluatePresenter
import kotlinx.android.synthetic.main.activity_evaluate.*

/**
 * 评估activity
 * Created by guanjianhui on 18-3-21.
 */
class EvaluateActivity : BaseActivity(), EvaluateContract.View {

    val mPresemter by lazy { EvaluatePresenter() }

    override fun initView() {
        mPresemter.attachView(this)

    }

    override fun initData() {
        mPresemter.getData()
    }

    override fun initListener() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_evaluate
    }

    override fun detachView() {
        mPresemter.detachView()
    }

    override fun loadSuccess(list: List<EvaluateItemBean>) {
        vp_evaluate.offscreenPageLimit=3
        vp_evaluate.adapter = EvaluateAdapter(list, this)
    }

    override fun loadFailure(msg: String) {

    }

}