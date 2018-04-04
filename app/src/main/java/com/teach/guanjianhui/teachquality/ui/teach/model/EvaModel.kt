package com.teach.guanjianhui.teachquality.ui.teach.model

import android.util.Log
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.property.Property
import com.teach.guanjianhui.teachquality.db.dao.QueryDao
import com.teach.guanjianhui.teachquality.db.table.TeachingTask_Table
import com.teach.guanjianhui.teachquality.scheduler.SchedulerUtils
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * 评价model
 * Created by guanjianhui on 18-3-26.
 */
class EvaModel {

    fun <E : BaseRXModel, T : BaseRXModel, R> getEvaDataList(clazzTask: Class<E>, clazzCon: Class<T>, orderColumn: Property<R>, isAscending: Boolean): Flowable<List<BaseRXModel>> {
        val singleTask = QueryDao<E>().query(clazzTask, TeachingTask_Table.teachingTaskNum, isAscending).compose(SchedulerUtils.ioToMain())
        val singleCon = QueryDao<T>().query(clazzCon, orderColumn, isAscending).compose(SchedulerUtils.ioToMain())
        return Single.concat(singleTask, singleCon)
    }
}