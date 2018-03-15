package com.teach.guanjianhui.teachquality.ui.teach.presenter

import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.db.table.StudentInfo_Table
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.ui.teach.contract.HomeContract
import com.teach.guanjianhui.teachquality.ui.teach.model.DBModel
import com.teach.guanjianhui.teachquality.ui.teach.model.HomeModel

/**
 * Created by guanjianhui on 18-1-30.
 */
class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private val dbModel by lazy { DBModel() }

    override fun getData() {

        addSubscription(
                dbModel.getQueryData(TeachTable.StudentInfo::class.java, StudentInfo_Table.sex, "女").subscribe { list ->
                    mRootView?.loadSuccess(list)
                })


    }

}