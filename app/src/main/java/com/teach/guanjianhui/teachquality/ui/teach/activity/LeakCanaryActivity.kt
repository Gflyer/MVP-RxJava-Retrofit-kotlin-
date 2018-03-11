package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.app.MyApplication
import com.teach.guanjianhui.teachquality.base.BaseActivity
import kotlinx.android.synthetic.main.activity_leakcanary.*

/**
 * 内存泄露测试
 * Created by guanjianhui on 18-3-9.
 */
class LeakCanaryActivity : BaseActivity(), View.OnClickListener {

    lateinit var myThread: Thread
    //lateinit var initThread: Thread
    val instance by lazy { this }
    //内部类引用外部activity的Context,使用instance延迟加载或者使用标签


    var handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0x01 -> {
                    //Toast.makeText(this@LeakCanaryActivity, "开始", Toast.LENGTH_SHORT).show()
                }
                0x02 -> {
                    //Toast.makeText(this@LeakCanaryActivity, "完成", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun layoutId(): Int {
        return R.layout.activity_leakcanary
    }

    override fun initView() {
    }

    override fun initData() {
        val initThread = LeakThread()
        initThread.start()
        Toast.makeText(this, "开始漏", Toast.LENGTH_SHORT).show()
    }

    override fun initListener() {
        btn_start.setOnClickListener(this)
        btn_finish.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start -> {
                myThread = object : Thread() {
                    override fun run() {
                        super.run()
                        var msg: Message = Message.obtain()
                        msg.what = 0x01
                        sleep(3000)
                        handler.sendMessageDelayed(msg, 3000)
                    }
                }
                myThread.start()

            }

            R.id.btn_finish -> {
                myThread.interrupt()
                var msg: Message = Message.obtain()
                msg.what = 0x02
                handler.sendMessage(msg)
            }
        }
    }

     inner class  LeakThread : Thread() {
        override fun run() {
            super.run()
            Thread.sleep(3000 * 20)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val refWatcher = MyApplication.getRefWatcher(this)
        refWatcher?.watch(this)
        //handler.removeCallbacksAndMessages(null)
    }

}