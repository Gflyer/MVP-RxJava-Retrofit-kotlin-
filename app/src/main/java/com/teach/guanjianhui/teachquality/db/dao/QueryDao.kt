package com.teach.guanjianhui.teachquality.db.dao

import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.language.RXSQLite
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.property.Property
import io.reactivex.Single

/**
 * Created by guanjianhui on 18-3-14.
 */
class QueryDao<E : BaseRXModel> : IQueryDao<E> {

    override fun <E> query(clazz: Class<E>): Single<List<E>> {
        return RXSQLite.rx(select.from(clazz)).queryList()
    }

    //查询
    override fun <E, T> query(clazz: Class<E>, column: Property<T>, value: T): Single<List<E>> {

        return RXSQLite.rx(select.from(clazz).where(column.`is`(value))).queryList()

    }
}