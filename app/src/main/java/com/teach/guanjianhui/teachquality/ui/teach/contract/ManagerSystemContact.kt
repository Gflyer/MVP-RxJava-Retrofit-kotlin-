package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.db.table.TeachTable

/**
 * Created by guanjianhui on 18-4-9.
 */
interface ManagerSystemContact {

    interface View : IBaseView {
        fun loadSuccess(list: List<TeachTable.DeadLine>)

        fun loadFailure(msg: String)

        fun updateCompete(msg:String)
    }

    interface Presenter {
        fun getData()

        fun updateData(item:TeachTable.DeadLine)
    }
}