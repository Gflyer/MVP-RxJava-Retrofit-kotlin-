package com.teach.guanjianhui.teachquality.db.dao

import com.raizlabs.android.dbflow.rx2.kotlinextensions.save
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import io.reactivex.Single

/**
 * UserDao实现类
 * Created by guanjianhui on 18-3-12.
 */
class AddDao<E : BaseRXModel> : IAddDao<E> {


    //保存单条数据
    override fun save(item: E): Single<Boolean> {
        return item.save()
    }

    //批量保存list
    override fun saveList(list: List<E>): Single<Boolean> {
        var single: Single<Boolean> = Single.create { emitter ->
            var isSuccess = true
            for (item in list) {
                if (isSuccess) {
                    item.save { isAdded ->
                        isSuccess = isAdded
                    }
                } else {
                    emitter.onSuccess(isSuccess)
                    break
                }
            }
            emitter.onSuccess(isSuccess)
        }
        return single
    }

    //插入
    override fun insert(item: E): Single<Long> {
        return item.insert()
    }


}