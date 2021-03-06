package com.teach.guanjianhui.teachquality.ui.teach.model


import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.property.Property
import com.teach.guanjianhui.teachquality.db.dao.AddDao
import com.teach.guanjianhui.teachquality.db.dao.DeleteDao
import com.teach.guanjianhui.teachquality.db.dao.QueryDao
import com.teach.guanjianhui.teachquality.db.dao.UpdateDao
import com.teach.guanjianhui.teachquality.scheduler.SchedulerUtils
import io.reactivex.Single

/**
 * 数据库查询的公用model类
 * Created by guanjianhui on 18-3-15.
 */
class DBModel {

    /**
     * 增加一条数据
     */
    fun <E : BaseRXModel> addData(item: E): Single<Boolean> {
        return AddDao<E>().save(item).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 批量增加数据
     */
    fun <E : BaseRXModel> addDataList(list: List<E>): Single<Boolean> {
        return AddDao<E>().saveList(list).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 插入一条数据
     */
    fun <E : BaseRXModel> insertData(item: E): Single<Long> {
        return AddDao<E>().insert(item).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 批量删除
     */
    fun <E : BaseRXModel, T> delDataList(clazz: Class<E>, column: Property<T>, value: T): Single<Boolean> {
        return DeleteDao<E, T>().delete(clazz, column, value).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 批量更新
     */
    fun <E : BaseRXModel> updateData(upList: List<E>): Single<Boolean> {
        return UpdateDao<E>().update(upList).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 更新一条数据
     */
    fun <E : BaseRXModel> updateData(item: E): Single<Boolean> {
        return UpdateDao<E>().update(item).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 查询数据根据条件
     */
    fun <E : BaseRXModel, T> getQueryData(clazz: Class<E>, column: Property<T>, value: T): Single<List<E>> {
        return QueryDao<E>().query(clazz, column, value).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 查询全部数据
     */
    fun <E : BaseRXModel> getQueryData(clazz: Class<E>): Single<List<E>> {
        return QueryDao<E>().query(clazz).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 按顺序简单查询
     */
    fun <E : BaseRXModel, R> getQueryData(clazz: Class<E>, orderColumn: Property<R>, isAscending: Boolean): Single<List<E>> {
        return QueryDao<E>().query(clazz, orderColumn, isAscending).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 按顺序查询
     */
    fun <E : BaseRXModel, T, R> getQueryData(clazz: Class<E>, column: Property<T>, value: T, orderColumn: Property<R>, isAscending: Boolean): Single<List<E>> {
        return QueryDao<E>().query(clazz, column, value, orderColumn, isAscending).compose(SchedulerUtils.ioToMain())
    }
}