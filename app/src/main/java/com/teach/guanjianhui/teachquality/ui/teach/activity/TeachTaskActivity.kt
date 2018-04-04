package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.beans.TeachTaskBean
import com.teach.guanjianhui.teachquality.db.table.Syllabus_Table
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.db.table.TeacherInfo_Table
import com.teach.guanjianhui.teachquality.ui.teach.adapter.TeachTaskAdapter
import com.teach.guanjianhui.teachquality.ui.teach.contract.TeachTaskContact
import com.teach.guanjianhui.teachquality.ui.teach.presenter.TeachTaskPresenter
import com.teach.guanjianhui.teachquality.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_teach_task.*
import kotlinx.android.synthetic.main.tool_title.*

/**
 * Created by guanjianhui on 18-3-29.
 */

class TeachTaskActivity : BaseActivity(), TeachTaskContact.View {
    val mPresenter: TeachTaskPresenter by lazy { TeachTaskPresenter() }
    var myList: ArrayList<TeachTaskBean>? = null
    var evaTypeNum: String? = null//教师编号
    var type: Int = 0//按类型查询，暂未区分


    override fun layoutId(): Int {
        return R.layout.activity_teach_task
    }

    override fun initView() {
        mPresenter.attachView(this)
        tv_title.text = "教师授课任务表"
        tv_end.text = "评分"
    }

    override fun initData() {
        evaTypeNum = intent.getStringExtra("evaTypeNum")
        type = intent.getIntExtra("type", 0)//按类型查询，暂未区分

        mPresenter.getTeachTaskData()
    }

    override fun initListener() {
        tv_end.setOnClickListener {
            val evaIntent = Intent(this, LeaderEvaActivity::class.java)
            evaIntent.putExtra("evaTypeNum", evaTypeNum)
            evaIntent.putExtra("type", type)
            startActivity(evaIntent)
        }

        tool_title.setNavigationOnClickListener {
            finish()
        }

    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun loadSuccess(list: List<TeachTable.TeachingTask>) {
        myList = ArrayList()
        for ((index, item) in list.withIndex()) {
            myList!!.add(TeachTaskBean(index + 1, getCourseName(item.dyllabusNum), getTeacherName(item.teacherNum), item.termNum))
        }
        var myAdapter = TeachTaskAdapter(this, myList!!)
        var layoutManager = LinearLayoutManager(this)
        rec_teach_task.layoutManager = layoutManager
        rec_teach_task.adapter = myAdapter
    }

    override fun loadFailure(msg: String) {
        ToastUtils.showToast(this, msg)
    }

    /**
     * 根据教师编号获取名字
     */
    private fun getTeacherName(teachNum: String): String {
        return SQLite.select(TeacherInfo_Table.name).from(TeachTable.TeacherInfo::class.java).where(TeacherInfo_Table.teacherNum.`is`(teachNum)).querySingle()!!.name
    }

    /**
     *  根据课程编号获取课名
     */
    private fun getCourseName(courseNum: String): String {
        return SQLite.select(Syllabus_Table.courseName).from(TeachTable.Syllabus::class.java).where(Syllabus_Table.courseNum.`is`(courseNum)).querySingle()!!.courseName
    }


}
