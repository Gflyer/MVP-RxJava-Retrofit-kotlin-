package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.content.Intent
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.constant.Field
import kotlinx.android.synthetic.main.activity_supervisor_choose.*

/**
 * Created by guanjianhui on 18-4-3.
 */
class SupervisorChooseActivity : BaseActivity() {
    private lateinit var supervisorNum: String

    override fun layoutId(): Int {
        return R.layout.activity_supervisor_choose
    }

    override fun initView() {
    }

    override fun initData() {
        supervisorNum = intent.getStringExtra("supervisorNum")
    }

    override fun initListener() {
        btn_supervisor_task.setOnClickListener {
            //授课任务
            val taskIntent = Intent(this, TeachTaskActivity::class.java)
            taskIntent.putExtra("evaTypeNum", supervisorNum)
            taskIntent.putExtra("type", Field.LOGIN_SUPERVISOR)
            startActivity(taskIntent)
        }
        btn_supervisor_eva.setOnClickListener {
            //价值评估
            val evaIntent = Intent(this, LeaderEvaActivity::class.java)
            evaIntent.putExtra("evaTypeNum", supervisorNum)
            evaIntent.putExtra("type", Field.LOGIN_SUPERVISOR)
            startActivity(evaIntent)
        }
    }

    override fun detachView() {
    }

}