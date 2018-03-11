package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.base.IPresenter
import com.teach.guanjianhui.teachquality.beans.ListItemBean

/**
 * Created by guanjianhui on 18-3-7.
 */
interface MineContract {
    interface View:IBaseView {
        //加载成功
        fun loadSuccess(msg:String)
        //加载失败
        fun loadFailure(msg: String?)
        //加载完成
        fun loadCompete(msg:String)

        fun setList(list:ArrayList<ListItemBean>)
    }

    interface Presenter {
        //请求获取数据
        fun getData(type:String,num:Int)
    }
}