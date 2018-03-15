package com.teach.guanjianhui.teachquality.db.dao

import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.kotlinextensions.delete
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.property.Property
import io.reactivex.Single

/**
 * Created by guanjianhui on 18-3-14.
 */
class DeleteDao<E : BaseRXModel, T> : IDeleteDao<E, T> {

    //删除
    override fun delete(clazz: Class<E>, column: Property<T>, value: T): Single<Boolean> {
        var delSingle: Single<Boolean> = Single.create({ emitter ->
            var delList = select.from(clazz).where(column.`is`(value)).queryList()
            var isSuccess = true
            if (delList != null) {
                for (item in delList) {
                    if (isSuccess) {
                        item.delete { isDel ->
                            isSuccess = isDel
                        }
                    } else {
                        emitter.onSuccess(false)
                        break
                    }
                }
                emitter.onSuccess(true)
            } else emitter.onSuccess(false)
        })
        return delSingle
    }
}