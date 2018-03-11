package com.teach.guanjianhui.teachquality.ui.teach.model

import com.teach.guanjianhui.teachquality.beans.ResponseBeans
import com.teach.guanjianhui.teachquality.net.RetrofitManager
import com.teach.guanjianhui.teachquality.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by guanjianhui on 18-3-7.
 */
class MineModel{
    fun getData(type:String,num:Int):Observable<ResponseBeans.ListResponseBean>{
        return RetrofitManager.service.getListData(type,num)
                .compose(SchedulerUtils.ioToMain())//上游子线程，下游主线程
    }
}