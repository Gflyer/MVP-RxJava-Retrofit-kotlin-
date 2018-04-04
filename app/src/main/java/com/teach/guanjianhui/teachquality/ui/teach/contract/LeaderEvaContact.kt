package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.beans.EvaTeachItem
import com.teach.guanjianhui.teachquality.db.table.TeachTable

/**
 * Created by guanjianhui on 18-3-28.
 */
interface LeaderEvaContact {

    interface View : IBaseView {
        fun loadTeacherSuccess(teacherNameList: ArrayList<TeachTable.TeachingTask>)

        fun saveEvaSuccess()

        fun loadFailure(msg:String)
    }

    interface Presenter {
        //查询评价学生列表
        fun getTeacherEvaData()

        fun getSupervisorEvaData()

        fun saveTeacherScore(workmateNum:String,item: EvaTeachItem)

        fun saveSupervisorScore(workmateNum:String,item: EvaTeachItem)

    }
}