package com.teach.guanjianhui.teachquality.beans

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * 底部栏item实现类
 * Created by guanjianhui on 18-1-29.
 */
class TabEntity(var title: String, var normalIcon: Int, var selectedIcon: Int) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int {
        return normalIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }
}