package com.teach.guanjianhui.teachquality.app

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

/**
 * Created by guanjianhui on 18-3-9.
 */
class MyApplication : Application() {

    private lateinit var refWatcher: RefWatcher

    override fun onCreate() {
        super.onCreate()
        refWatcher = setupLeakCanary()
    }

    private fun setupLeakCanary(): RefWatcher {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED
        } else {
            return LeakCanary.install(this)
        }
    }

    //相当于static
    companion object {
        fun getRefWatcher(ctx: Context): RefWatcher? {
            val myApplication = ctx.applicationContext as MyApplication
            return myApplication.refWatcher
        }
    }
}