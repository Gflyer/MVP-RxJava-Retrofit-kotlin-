package com.teach.guanjianhui.teachquality.ui.teach.fragment.testAac

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.teach.guanjianhui.teachquality.beans.ResponseBeans
import com.teach.guanjianhui.teachquality.net.RetrofitManager
import com.teach.guanjianhui.teachquality.scheduler.SchedulerUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by guanjianhui on 18-2-1.
 */
class TestViewModel : ViewModel() {
    private val testLiveData: MutableLiveData<ResponseBeans.TestResponseBean> by lazy { MutableLiveData<ResponseBeans.TestResponseBean>() }

    fun getUserId(): MutableLiveData<ResponseBeans.TestResponseBean> {
        return testLiveData
    }

    //编写网络请求逻辑
    fun getListData() {
        RetrofitManager.service.getTestList().compose(SchedulerUtils.ioToMain()).subscribe(object : Observer<ResponseBeans.TestResponseBean> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: ResponseBeans.TestResponseBean) {
                testLiveData.setValue(t)
            }

            override fun onError(e: Throwable) {

            }
        })//传Observer

    }

}
