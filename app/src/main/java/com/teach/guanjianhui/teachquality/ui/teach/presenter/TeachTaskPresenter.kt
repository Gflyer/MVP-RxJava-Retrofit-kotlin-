package com.teach.guanjianhui.teachquality.ui.teach.presenter

import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.db.table.TeachingTask_Table
import com.teach.guanjianhui.teachquality.ui.teach.contract.TeachTaskContact
import com.teach.guanjianhui.teachquality.ui.teach.model.DBModel

/**
 * Created by guanjianhui on 18-3-29.
 */
class TeachTaskPresenter : BasePresenter<TeachTaskContact.View>(), TeachTaskContact.Presenter {
    val dbModel by lazy { DBModel() }

    override fun getTeachTaskData() {
        dbModel.getQueryData(TeachTable.TeachingTask::class.java).subscribe({ list ->
            mRootView?.loadSuccess(list)
        })
    }

    override fun getTeachTaskDataByTerm(termNum: String) {
        dbModel.getQueryData(TeachTable.TeachingTask::class.java, TeachingTask_Table.termNum, termNum).subscribe({ list ->
            mRootView?.loadSuccess(list)
        })
    }

}
