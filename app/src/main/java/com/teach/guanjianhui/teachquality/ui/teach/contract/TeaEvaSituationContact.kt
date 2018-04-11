package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.beans.TeaSituationBean
import com.teach.guanjianhui.teachquality.db.table.TeachTable

/**
 * Created by guanjianhui on 18-4-10.
 */
interface TeaEvaSituationContact {
    interface View : IBaseView {
        fun loadSuccess(list: List<TeachTable.TeachingTask>)

        fun loadDataSuccess(list: List<TeaSituationBean>)

        fun loadFailure()
    }

    interface Presenter {
        fun getData()

        fun getEvaData(teachTaskNum: String, id: Int)
    }
}
