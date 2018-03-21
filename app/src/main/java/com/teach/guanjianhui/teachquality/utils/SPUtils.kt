package com.teach.guanjianhui.teachquality.utils

import android.content.Context
import android.content.SharedPreferences
import com.teach.guanjianhui.teachquality.MyApplication

/**
 * Created by guanjianhui on 18-3-21.
 */
object SPUtils {

    private val file_name = "teach_quality_config"
    private var sp: SharedPreferences? = null


    fun putInt(key: String, value: Int) {
        if (sp == null) {
            sp = MyApplication.context.getSharedPreferences(SPUtils.file_name, Context.MODE_PRIVATE)
        }
        sp!!.edit()!!.putInt(key, value)!!.commit()
    }

    fun getInt(key: String, defValue: Int): Int {
        if (sp == null) {
            sp = MyApplication.context.getSharedPreferences(SPUtils.file_name, Context.MODE_PRIVATE)
        }
        return sp!!.getInt(key, defValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        if (sp == null) {
            sp = MyApplication.context.getSharedPreferences(SPUtils.file_name, Context.MODE_PRIVATE)
        }
        sp!!.edit()!!.putBoolean(key, value)!!.commit()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        if (sp == null) {
            sp = MyApplication.context.getSharedPreferences(SPUtils.file_name, Context.MODE_PRIVATE)
        }
        return sp!!.getBoolean(key, defValue)
    }

    fun putString(key: String, value: String) {
        if (sp == null) {
            sp = MyApplication.context.getSharedPreferences(SPUtils.file_name, Context.MODE_PRIVATE)
        }
        sp!!.edit()!!.putString(key, value)!!.commit()
    }

    fun getString(key: String, defValue: String): String {
        if (sp == null) {
            sp = MyApplication.context.getSharedPreferences(SPUtils.file_name, Context.MODE_PRIVATE)
        }
        return sp!!.getString(key, defValue)
    }

}