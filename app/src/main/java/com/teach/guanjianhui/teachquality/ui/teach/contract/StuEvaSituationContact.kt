package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.beans.StuSituationBean

/**
 * Created by guanjianhui on 18-4-9.
 */
interface StuEvaSituationContact{
    interface View:IBaseView{
        fun loadSuccess(list: List<StuSituationBean>)

        fun loadFailure()
    }

    interface Presenter{
        fun getData()

    }
}