package com.teach.guanjianhui.teachquality.db

import com.raizlabs.android.dbflow.annotation.Database

/**
 * 配置数据库版本号,名称
 * Created by guanjianhui on 18-3-12.
 */
@Database(name = TeachDatabase.NAME, version = TeachDatabase.VERSION)
object TeachDatabase {

    const val NAME = "TeachDatabase"

    const val VERSION = 1
}
