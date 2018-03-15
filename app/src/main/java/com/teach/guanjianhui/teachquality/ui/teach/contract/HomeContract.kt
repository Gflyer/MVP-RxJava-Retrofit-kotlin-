package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.base.IPresenter
import com.teach.guanjianhui.teachquality.db.table.TeachTable

/**
 * Created by guanjianhui on 18-1-30.
 */
interface HomeContract{

    interface View:IBaseView{
        //获取数据成功
        fun loadSuccess(list: List<TeachTable.StudentInfo>)

    }

    interface Presenter:IPresenter<View> {
        //请求获取数据
        fun getData()
    }
}