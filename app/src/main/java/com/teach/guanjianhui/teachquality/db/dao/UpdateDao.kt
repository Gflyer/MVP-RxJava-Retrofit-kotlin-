package com.teach.guanjianhui.teachquality.db.dao

import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.kotlinextensions.update
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.sql.language.property.Property
import io.reactivex.Single

/**
 * Created by guanjianhui on 18-3-14.
 */
class UpdateDao<E : BaseRXModel> : IUpdateDao<E> {

    /**
     * 根据item进行修改
     */
    override fun update(upList: List<E>): Single<Boolean> {
        var upSingle: Single<Boolean> = Single.create({ emitter ->
            var isSuccess = true
            for ((index, item) in upList.withIndex()) {
                if (isSuccess) {
                    item.update { isUp ->
                        isSuccess = isUp
                    }
                } else {
                    emitter.onError(Throwable("更新第" + index + "条失败，请检查数据！！！"))
                    break
                }
            }
            emitter.onSuccess(true)
        })
        return upSingle
    }

    /**
     * 根据条件修改
     */
    override fun <N, O> update(clazz: Class<E>, upColumn: Property<N>, upValue: N, oldColumn: Property<O>, oldValue: O): Single<Boolean> {
        var upSingle: Single<Boolean> = Single.create({ emitter ->
           var upList=select.from(clazz).where(oldColumn.`is`(oldValue)).queryList()
            for (item in upList){

            }
        })
        return upSingle
    }

}