package com.teach.guanjianhui.teachquality.ui.teach.presenter

import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.ui.teach.contract.MineContract
import com.teach.guanjianhui.teachquality.ui.teach.model.MineModel
import io.reactivex.disposables.Disposable

/**
 * Created by guanjianhui on 18-3-7.
 */
class MinePresenter : BasePresenter<MineContract.View>(), MineContract.Presenter {
    val mineModel = MineModel()


    override fun getData(type: String, num: Int) {
        val disposable: Disposable = mineModel.getData(type, num).subscribe({ responseBean ->
            mRootView?.apply {
                loadSuccess(responseBean.msg)
                setList(responseBean.data)
            }
        }, { error ->
            mRootView?.apply {
                loadFailure(error.message)
            }
        }, {
            mRootView?.loadCompete("加载完成")
        }
        )

        addSubscription(disposable)
    }

}