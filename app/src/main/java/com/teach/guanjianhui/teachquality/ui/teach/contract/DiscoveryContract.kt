package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.base.IPresenter

/**
 * 契约类
 * Created by guanjianhui on 18-1-29.
 */
interface DiscoveryContract {

    interface View : IBaseView {
        /*
        *显示成功
        */
        fun showSuccess(res:Int)
    }

    interface Presenter {
        /*
        * 获取数据
        */
        fun getData()
    }


}