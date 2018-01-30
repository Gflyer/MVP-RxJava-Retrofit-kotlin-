package com.teach.guanjianhui.teachquality.scheduler

import io.reactivex.ObservableTransformer
import javax.xml.transform.Transformer
import io.reactivex.schedulers.Schedulers


/**
 * Created by guanjianhui on 18-1-30.
 */
object SchedulerUtils {
    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}