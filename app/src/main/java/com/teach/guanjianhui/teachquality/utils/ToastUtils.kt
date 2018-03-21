package com.teach.guanjianhui.teachquality.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by guanjianhui on 18-3-21.
 */
object ToastUtils {

    fun showToast(ctx: Context, msg: String?) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(ctx: Context, msg: Int) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }

}