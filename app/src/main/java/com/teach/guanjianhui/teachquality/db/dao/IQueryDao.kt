package com.teach.guanjianhui.teachquality.db.dao

import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.property.Property
import io.reactivex.Single

/**
 * Created by guanjianhui on 18-3-14.
 */
interface IQueryDao<E : BaseRXModel> {


    /**
     * 关联查询（按条件否定）
     */
    fun <E, T, N : BaseRXModel> query(clazz: Class<E>, column: Property<T>, clazzNot: Class<N>): Single<List<E>>


    /**
     * 查询(按条件)
     */
    fun <E, T> query(clazz: Class<E>, column: Property<T>, value: T): Single<List<E>>

    /**
     * 查询(升降序)
     */
    fun <E, T, R> query(clazz: Class<E>, column: Property<T>, value: T, orderColumn: Property<R>, isAscending: Boolean): Single<List<E>>

    /**
     * 简单查询
     */
    fun <E> query(clazz: Class<E>): Single<List<E>>

    /**
     * 简单查询（升降序）
     */
    fun <E, R> query(clazz: Class<E>, orderColumn: Property<R>, isAscending: Boolean): Single<List<E>>


}