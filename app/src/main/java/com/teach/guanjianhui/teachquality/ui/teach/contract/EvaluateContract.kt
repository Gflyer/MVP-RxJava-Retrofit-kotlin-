package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.beans.EvaluateItemBean

/**
 * Created by guanjianhui on 18-3-21.
 */
interface EvaluateContract {
    interface View : IBaseView {
        fun loadSuccess(list: List<EvaluateItemBean>)

        fun loadFailure(msg:String)
    }

    interface Presenter {
        fun getData()
    }
}