package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.base.IPresenter

/**
 * Created by guanjianhui on 18-3-7.
 */
interface MineContract {
    interface View:IBaseView {
        //加载成功
        fun loadSuccess()
        //加载失败
        fun loadFailure()
    }

    interface Presenter:IPresenter<View> {
        //请求获取数据
        fun getData()
    }
}