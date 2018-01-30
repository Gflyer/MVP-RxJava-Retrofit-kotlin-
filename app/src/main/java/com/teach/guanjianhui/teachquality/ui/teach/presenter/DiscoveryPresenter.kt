package com.teach.guanjianhui.teachquality.ui.teach.presenter

import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.ui.teach.contract.DiscoveryContract
import com.teach.guanjianhui.teachquality.ui.teach.model.DiscoveryModel

/**
 * Created by guanjianhui on 18-1-29.
 */
class DiscoveryPresenter : BasePresenter<DiscoveryContract.View>(), DiscoveryContract.Presenter {

    private val mModel: DiscoveryModel by lazy { DiscoveryModel() }
    //请求获取数据,封装rxjava
    override fun getData() {

        mRootView?.showSuccess(mModel.getData())
    }


}
