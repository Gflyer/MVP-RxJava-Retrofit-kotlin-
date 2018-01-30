package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.base.IPresenter

/**
 * Created by guanjianhui on 18-1-30.
 */
interface HomeContact{

    interface View:IBaseView{
        //获取数据成功
        fun loadSuccess(res:Int)

    }

    interface Presenter:IPresenter<View> {
        //请求获取数据
        fun getData()
    }
}