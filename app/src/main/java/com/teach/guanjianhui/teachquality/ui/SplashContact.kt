package com.teach.guanjianhui.teachquality.ui

import com.teach.guanjianhui.teachquality.base.IBaseView

/**
 * Created by guanjianhui on 18-3-22.
 */
interface SplashContact {

    interface View : IBaseView {

        fun saveSuccess(msg:String)

        fun saveFailure()

        fun startActivity()
    }

    interface Presenter {
        fun saveEvaluateData()
    }
}