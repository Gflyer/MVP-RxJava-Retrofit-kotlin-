package com.teach.guanjianhui.teachquality.db.dao

import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import io.reactivex.Single

/**
 * 数据库增加,插入,删除接口
 * Created by guanjianhui on 18-3-12.
 */

interface IAddDao<E : BaseRXModel> {

    /*保存一条数据*/
    fun save(item: E): Single<Boolean>

    /*批量保存*/
    fun saveList(list: List<E>): Single<Boolean>

    /*增加*/
    fun insert(item: E): Single<Long>


}
