package com.teach.guanjianhui.teachquality.db.dao

import com.raizlabs.android.dbflow.kotlinextensions.list
import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.language.RXSQLite
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.sql.language.property.Property
import io.reactivex.Single

/**
 * Created by guanjianhui on 18-3-14.
 */
class QueryDao<E : BaseRXModel> : IQueryDao<E> {

    //按顺序简单查询
    override fun <E, R> query(clazz: Class<E>, orderColumn: Property<R>, isAscending: Boolean): Single<List<E>> {
        return RXSQLite.rx(select.from(clazz).orderBy(orderColumn,isAscending)).queryList()
    }


    //否定查询
    override fun <E, T, N : BaseRXModel> query(clazz: Class<E>, column: Property<T>, clazzNot: Class<N>): Single<List<E>> {
        return RXSQLite.rx(select.from(clazz).where(column.isNot(SQLite.select(column).from(clazzNot)))).queryList()

    }

    override fun <E> query(clazz: Class<E>): Single<List<E>> {
        return RXSQLite.rx(select.from(clazz)).queryList()
    }

    //查询
    override fun <E, T> query(clazz: Class<E>, column: Property<T>, value: T): Single<List<E>> {
        return RXSQLite.rx(select.from(clazz).where(column.`is`(value))).queryList()
    }

    //按顺序查询
    override fun <E, T, R> query(clazz: Class<E>, column: Property<T>, value: T, orderColumn: Property<R>, isAscending: Boolean): Single<List<E>> {
        return RXSQLite.rx(select.from(clazz).where(column.`is`(value)).orderBy(orderColumn,isAscending)).queryList()
    }

}