package com.teach.guanjianhui.teachquality.db.dao

import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.property.Property
import io.reactivex.Single

/**
 * Created by guanjianhui on 18-3-14.
 */
interface IQueryDao<E: BaseRXModel,T>{

    /**
     * 查询
     */
    fun <E>query(clazz: Class<E>, column: Property<T>, value: T): Single<List<E>>
}