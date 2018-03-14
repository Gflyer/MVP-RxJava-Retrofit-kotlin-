package com.teach.guanjianhui.teachquality.db

/**
 * 数据库查询接口
 * Created by guanjianhui on 18-3-12.
 */

interface UserDao {

    /*增加*/
    fun insert()

    /*删除*/
    fun delete()

    /*修改*/
    fun update()

    /*查询*/
    fun query()
}
