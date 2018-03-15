package com.teach.guanjianhui.teachquality.ui.teach.model

import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.property.Property
import com.teach.guanjianhui.teachquality.db.dao.QueryDao
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.scheduler.SchedulerUtils
import io.reactivex.Single

/**
 * Created by guanjianhui on 18-1-30.
 */
class HomeModel {

    /**
     * 查询数据
     */
    fun <E : BaseRXModel, T> getQueryData(clazz: Class<E>, column: Property<T>, value: T): Single<List<E>> {
        return QueryDao<E, T>().query(clazz, column, value).compose(SchedulerUtils.ioToMain())
    }
}
