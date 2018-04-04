package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.db.table.TeachTable

/**
 * Created by guanjianhui on 18-3-29.
 */
interface TeachTaskContact {
    interface View : IBaseView {
        fun loadSuccess(list: List<TeachTable.TeachingTask>)

        fun loadFailure(msg:String)
    }

    interface Presenter {
        //查询所有
        fun getTeachTaskData()

        //按学期查找
        fun getTeachTaskDataByTerm(termNum:String)
    }
}