package com.teach.guanjianhui.teachquality.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 *
 * Created by guanjianhui on 18-1-26.
 */
open class BasePresenter<T : IBaseView> : IPresenter<T> {

    var mRootView: T? = null
        private set
    val compositeDisposable:CompositeDisposable by lazy { CompositeDisposable() }

    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    //解除view引用，避免内存泄露
    override fun detachView() {
        mRootView = null
        if(!compositeDisposable.isDisposed){
            compositeDisposable.clear()//取消订阅
        }

    }

    fun addSubscription(disposable:Disposable){
        compositeDisposable.add(disposable)
    }

}