package com.teach.guanjianhui.teachquality.db.dao

import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.property.Property
import io.reactivex.Single

/**
 * Created by guanjianhui on 18-3-14.
 */
interface IUpdateDao<E : BaseRXModel> {

    /**
     * 指定list更新
     */
    fun update(upList:List<E>):Single<Boolean>

    /**
     *条件更新
     */
    fun <N, O> update(clazz: Class<E>, upColumn: Property<N>, upValue: N, oldColumn: Property<O>, oldValue: O):Single<Boolean>

}