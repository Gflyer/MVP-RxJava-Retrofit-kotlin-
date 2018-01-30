package com.teach.guanjianhui.teachquality.ui.teach.presenter

import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.ui.teach.contract.HomeContact
import com.teach.guanjianhui.teachquality.ui.teach.model.HomeModel

/**
 * Created by guanjianhui on 18-1-30.
 */
class HomePresenter : BasePresenter<HomeContact.View>(), HomeContact.Presenter {

    private val homeModel by lazy { HomeModel() }

    override fun getData() {
        mRootView?.loadSuccess(homeModel.getData())
    }

}