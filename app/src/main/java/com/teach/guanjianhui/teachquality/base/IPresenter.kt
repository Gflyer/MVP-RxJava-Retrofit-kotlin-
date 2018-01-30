package com.teach.guanjianhui.teachquality.base

/**
 * presenter基类
 *
 * Created by guanjianhui on 18-1-26.
 */
interface IPresenter<in V : IBaseView> {

    //绑定view
    fun attachView(mRootView: V)

    //解绑view
    fun detachView()
}
