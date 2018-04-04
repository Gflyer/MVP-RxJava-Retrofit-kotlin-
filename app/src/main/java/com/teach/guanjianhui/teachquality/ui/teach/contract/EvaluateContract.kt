package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.beans.EvaluateItemBean
import com.teach.guanjianhui.teachquality.beans.EvaluateStuBean
import com.teach.guanjianhui.teachquality.db.table.TeachTable

/**
 * Created by guanjianhui on 18-3-21.
 */
interface EvaluateContract {
    interface View : IBaseView {
        fun loadSuccess(list: List<EvaluateItemBean>)

        fun loadTeacherSuccess(list: List<TeachTable.TeachingTask>)

        fun loadFailure(msg:String)

        fun saveEvaSuccess()
    }

    interface Presenter {
        fun getData()

        fun getTeacherList()

        fun saveTeacherEva(list:List<EvaluateStuBean>,teachingTaskNum:String?,studentNum:String?)
    }


}