package com.teach.guanjianhui.teachquality.ui.teach.presenter

import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.ui.teach.contract.ManagerSystemContact
import com.teach.guanjianhui.teachquality.ui.teach.model.DBModel

/**
 *
 * Created by guanjianhui on 18-4-9.
 */
class ManagerSystemPresenter : BasePresenter<ManagerSystemContact.View>(), ManagerSystemContact.Presenter {

    private val dbModel by lazy { DBModel() }

    override fun getData() {
        dbModel.getQueryData(TeachTable.DeadLine::class.java).subscribe({ list ->
            mRootView?.loadSuccess(list)
        }, {
            mRootView?.loadFailure("加载失败")
        })
    }

    override fun updateData(item: TeachTable.DeadLine) {
        dbModel.updateData(item).subscribe { isSuccess ->
            if (isSuccess) mRootView?.updateCompete("日期更改成功") else mRootView?.updateCompete("日期更改失败")
        }

    }

}