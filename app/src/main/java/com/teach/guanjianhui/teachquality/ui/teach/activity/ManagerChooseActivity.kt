package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.content.Intent
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import kotlinx.android.synthetic.main.activity_manager_choose.*

/**
 * Created by guanjianhui on 18-4-4.
 */
class ManagerChooseActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_manager_choose
    }

    override fun initView() {
        setFontDark()
    }

    override fun initData() {
    }

    override fun initListener() {
        btn_eva_manage.setOnClickListener {
            startActivity(Intent(this, ManagerSystemActivity::class.java))
        }
        btn_eva_student.setOnClickListener {
            startActivity(Intent(this, StuEvaSituationActivity::class.java))
        }
        btn_eva_teacher.setOnClickListener {
            startActivity(Intent(this,TeaEvaSituationActivity::class.java))
        }
    }

    override fun detachView() {
    }

}