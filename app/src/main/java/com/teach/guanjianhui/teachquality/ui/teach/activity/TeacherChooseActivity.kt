package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.content.Intent
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.constant.Field
import kotlinx.android.synthetic.main.activity_teacherchoose.*

/**
 * Created by guanjianhui on 18-3-29.
 */
class TeacherChooseActivity : BaseActivity() {

    var teacherNum: String? = null//教师编号

    override fun initView() {
        setFontDark()//设置状态栏字体为黑色
    }

    override fun initData() {
        teacherNum = intent.getStringExtra("teacherNum")
    }

    override fun initListener() {
        //授课任务
        btn_teach_task.setOnClickListener {
            val teachTaskIntent = Intent(this, TeachTaskActivity::class.java)
            teachTaskIntent.putExtra("evaTypeNum", teacherNum)
            teachTaskIntent.putExtra("type", Field.LOGIN_TEACHER)
            startActivity(teachTaskIntent)
        }
        //评价情况
        btn_evaluate_situation.setOnClickListener {
            startActivity(Intent(this, EvaDetialActivity::class.java))

        }
        //同行评价
        btn_workmate_eva.setOnClickListener {
            val workmateIntent = Intent(this, LeaderEvaActivity::class.java)
            workmateIntent.putExtra("evaTypeNum", teacherNum)
            workmateIntent.putExtra("type", Field.LOGIN_TEACHER)
            startActivity(workmateIntent)
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_teacherchoose
    }

    override fun detachView() {

    }
}
